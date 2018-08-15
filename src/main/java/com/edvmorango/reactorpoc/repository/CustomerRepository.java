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


    public CustomerRepository() throws Exception {

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://0.0.0.0:3306/webfluxpoc", "root", "123");

        NonBlockingConnectionPool pool = Pools.nonBlocking().maxPoolSize(Runtime.getRuntime().availableProcessors() * 2)
                .connectionProvider(ConnectionProvider.from(connection))
                .build();

        this.db = Database.from(pool);

    }

    public Mono<Integer> create(Mono<Customer> customer) {

        String sql = "INSERT INTO TB_CUSTOMER(name, email) VALUES (?, ?)";

        return customer.flatMap(c -> {
            var res = db.update(sql).parameters(c.getName(), c.getEmail()).returnGeneratedKeys().getAs(Integer.class);
            return Mono.from(res);
        });

    }


    public Mono<Customer> findById(Long id) {

        String sql = "SELECT * FROM TB_CUSTOMER WHERE id = ?";

        return Mono.from(db.select(sql).parameter(id).getAs(Customer.class));

    }


    public Flux<Customer> list() {

        String sql = "SELECT * FROM TB_CUSTOMER";

        return Flux.from(db.select(sql).getAs(Customer.class));

    }


}
