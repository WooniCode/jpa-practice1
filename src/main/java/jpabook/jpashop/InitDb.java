package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = createMember("userA", "Seoul", "1", "1111");

            Book book1 = createBook("JPA1 BOOK", 10000, 100);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);

            OrderItem orderItem1 = createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private OrderItem createOrderItem(Book book1, int i, int i2) {
            return OrderItem.createOrderItem(book1, i, i2);
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            em.persist(book1);
            return book1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            em.persist(member);
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB", "Busan", "2", "2111");

            Book book1 = createBook("Spring1 BOOK", 10000, 100);

            Book book2 = createBook("Spring2 BOOK", 20000, 100);

            OrderItem orderItem1 = createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = createOrderItem(book2, 20000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}