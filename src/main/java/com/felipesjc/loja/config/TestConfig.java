package com.felipesjc.loja.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipesjc.loja.model.Address;
import com.felipesjc.loja.model.Category;
import com.felipesjc.loja.model.Order;
import com.felipesjc.loja.model.OrderItem;
import com.felipesjc.loja.model.Payment;
import com.felipesjc.loja.model.Product;
import com.felipesjc.loja.model.User;
import com.felipesjc.loja.model.enums.OrderStatus;
import com.felipesjc.loja.repositories.AddressRepository;
import com.felipesjc.loja.repositories.CategoryRepository;
import com.felipesjc.loja.repositories.OrderItemRepository;
import com.felipesjc.loja.repositories.OrderRepository;
import com.felipesjc.loja.repositories.ProductRepository;
import com.felipesjc.loja.repositories.UserRepository;
import com.felipesjc.loja.service.ViaCepService;

/**
 * Classe usada para testar a aplicação
 *
 * 
 * 
 * @author Felipesjc17
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;	
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public void run(String... args) throws Exception {
		

		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers"); 
		
		Product p1 = new Product(null, "Mente milionária", "Livro pra ficar rico.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "50 polegadas.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Caro demais.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "16GB de ram.", 1200.0, ""); 
		Product p5 = new Product(null, "Crise", "Livro sobre a crise.", 100.99, ""); 
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		Address ad1 = checkAddress("12237150", "175");
		Address ad2 = checkAddress("12232571", "150");
		
		
		User u1 = new User(null, "Maria", "maria@gmail.com", "988888888", "123456", ad1); 
		User u2 = new User(null, "Alex", "alex@gmail.com", "977777777", "123456", ad2);
		
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}
	private Address checkAddress(String cep, String number) {
			Address address = viaCepService.consultCep(cep);
			address.setNumber(number);
			addressRepository.save(address);
			return address;
		
	}
	
}
