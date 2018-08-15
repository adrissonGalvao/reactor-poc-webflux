package com.edvmorango.reactorpoc.repository;

import com.edvmorango.reactorpoc.model.Customer;
import io.reactivex.Flowable;
import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.NonBlockingConnectionPool;
import org.davidmoten.rx.jdbc.pool.Pools;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Connection;
import java.sql.DriverManager;

public class CustomerRepository {
    private Database db;


    public CustomerRepository() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://0.0.0.0:3306/webfluxpoc", "root", "123");


            NonBlockingConnectionPool pool = Pools.nonBlocking().maxPoolSize(Runtime.getRuntime().availableProcessors() * 2)
                    .connectionProvider(ConnectionProvider.from(connection))
                    .build();

            this.db = Database.from(pool);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Mono<Long> create(Mono<Customer> customer) {

        String sql = "INSERT INTO TB_CUSTOMER(name, email) VALUES (?, ?)";

        return customer.flatMap(c -> {
            var res = db.update(sql).parameters(c.getName(), c.getEmail()).returnGeneratedKeys().getAs(Long.class);
            return Mono.from(res);
        });

    }

    public Mono<Long> create(Customer customer) {

        String sql = "INSERT INTO TB_CUSTOMER(name, email) VALUES (?, ?)";

        return Mono.from(
                db.update(sql)
                        .parameters(customer.getName(),
                                    customer.getEmail())
                        .returnGeneratedKeys()
                        .getAs(Long.class));
    }


    public Mono<Customer> findById(Long id) {

        String sql = "SELECT * FROM TB_CUSTOMER WHERE id = ?";

        return Mono.from(db.select(sql).parameter(id)
                .get(rs -> new Customer(rs.getLong("id"),
                                        rs.getString("name"),
                                        rs.getString("email"))));

    }


    public Flux<Customer> list() {

        String sql = "SELECT * FROM TB_CUSTOMER";

        return Flux.from(db.select(sql).getAs(Customer.class));

    }


}
