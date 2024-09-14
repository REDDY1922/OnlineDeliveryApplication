package com.example.OnlineDeliveryApplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.dto.OrderDTO;
import com.example.OnlineDeliveryApplication.models.Customer;
import com.example.OnlineDeliveryApplication.models.Orders;
import com.example.OnlineDeliveryApplication.models.Product;
import com.example.OnlineDeliveryApplication.services.CustomerService;
import com.example.OnlineDeliveryApplication.services.OrdersService;
import com.example.OnlineDeliveryApplication.services.ProductService;
import com.example.OnlineDeliveryApplication.services.UserService;

@RestController
@RequestMapping("/orders")
public class OrdersController<Order>{
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrdersService ordersService;
	@PostMapping("/saveall")
	public ResponseEntity<?> orderProducts(@RequestBody List<OrderDTO> orderDTOList) {
	    List<Orders> ordersList = new ArrayList<>();

	    for (OrderDTO orderDTO : orderDTOList) {
	        try {
	            Orders order = getOrders(orderDTO);
	            ordersList.add(order);

	            // Send an email to the customer who made the order
	            userService.sendEmailOnOrderPlaced(order.getCustomer().getCustomerId());
	            
	        } catch (InvalidIdException e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().body("Error processing order: " + e.getMessage());
	        }
	    }

	    return ResponseEntity.ok().body(ordersService.insert(ordersList));
	}

	public Orders getOrders(OrderDTO orderDTO) throws InvalidIdException {
	    Product product = productService.getProductById(orderDTO.getPid());
	    Customer customer = customerService.getCustomer(orderDTO.getCid());

	    Orders order = new Orders();
	    order.setCustomer(customer);
	    order.setProduct(product);
	    order.setQuantity(orderDTO.getQuantity());
	    // Calculate the total price
	    double totalPrice = orderDTO.getQuantity() * product.getPrice();
	    order.setTotalAmount(totalPrice);

	    return order;
	}
	
	 
		
		
	     @GetMapping("/getBYCustomerId/{cid}") 
	 	public ResponseEntity<?> getOrders(@PathVariable int cid) {
	 		List<Orders> listOfOrders=null;
				 listOfOrders=ordersService.getMyOrders(cid);
	       return new ResponseEntity<List<Orders>>(listOfOrders,HttpStatus.OK);
	 	}
	     

	     
	     @SuppressWarnings("unchecked")
		@GetMapping("/order/getall")
	     public List<Order> getAllOrders(){
	 		return (List<Order>) productService.getAll();
	 	}
		
	     
	    
	     @DeleteMapping("/delete/{id}")
			public ResponseEntity<?> deleteorder(@PathVariable int id) {
				try {

					ordersService.deleteOrder(id);
				} catch (Exception e) {
					return ResponseEntity.badRequest().body(e.getMessage());
				}
				return ResponseEntity.ok().body("order deleted successfully");
			}

}


