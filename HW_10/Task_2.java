package HW_10;

// Часть 2: Паттерн Репозиторий

import java.util.List;

public interface OrderRepository {
    void saveOrder(Order order);
    Order loadOrderById(int orderId);
    List<Order> loadAllOrders();
}

public class OrderRepositoryImpl implements OrderRepository {
    private DatabaseConnection databaseConnection;

    public OrderRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void saveOrder(Order order) {
        // Save the order to the database
        databaseConnection.execute("INSERT INTO orders (id, customer, total_amount, order_date) VALUES (?, ?, ?, ?)",
                order.getId(), order.getCustomer(), order.getTotalAmount(), order.getOrderDate());

        // Save the order items to the database
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            databaseConnection.execute("INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)",
                    order.getId(), orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getPrice());
        }
    }

    @Override
    public Order loadOrderById(int orderId) {
        // Load the order from the database
        Object[] orderData = databaseConnection.querySingleRow("SELECT * FROM orders WHERE id = ?", orderId);
        Order order = new Order(
                (int) orderData[0],
                (String) orderData[1],
                (double) orderData[2],
                (Date) orderData[3]);

        // Load the order items from the database
        List<Object[]> orderItemsData = databaseConnection.query("SELECT * FROM order_items WHERE order_id = ?", orderId);
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object[] orderItemData : orderItemsData) {
            Product product = new Product((int) orderItemData[1], (String) orderItemData[2]);
            OrderItem orderItem = new OrderItem(
                    (int) orderItemData[0],
                    product,
                    (int) orderItemData[3],
                    (double) orderItemData[4]);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        return order;
    }

    @Override
    public List<Order> loadAllOrders() {
        // Load all orders from the database
        List<Order> orders = new ArrayList<>();
        List<Object[]> ordersData = databaseConnection.query("SELECT * FROM orders");
        for (Object[] orderData : ordersData) {
            Order order = new Order(
                    (int) orderData[0],
                    (String) orderData[1],
                    (double) orderData[2],
                    (Date) orderData[3]);

            // Load the order items from the database
            List<Object[]> orderItemsData = databaseConnection.query("SELECT * FROM order_items WHERE order_id = ?", order.getId());
            List<OrderItem> orderItems = new ArrayList<>();
            for (Object[] orderItemData : orderItemsData) {
                Product product = new Product((int) orderItemData[1], (String) orderItemData[2]);
                OrderItem orderItem = new OrderItem(
                        (int) orderItemData[0],
                        product,
                        (int) orderItemData[3],
                        (double) orderItemData[4]);
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);

            orders.add(order);
        }

        return orders;
    }
}