package GUI;

import Console.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCentre implements ActionListener, ListSelectionListener {

    private User currentUser;
    private JButton checkOutBtn;
    private ArrayList<Product> listOfProducts;

    private JLabel selectCategory = new JLabel("Select Product Category ");
    private JLabel productDetails = new JLabel("Selected Product - Details");
    private JLabel productInfo;

    private JComboBox<String> categories;

    private JButton shoppingCart = new JButton("Shopping Cart");
    private JButton addToShoppingCart = new JButton("Add to Shopping Cart");

    private JTable productTable;
    private DefaultTableModel tableModel;

    private ShoppingCart cart;

    private int userPurchaseCount = 0;

    ShoppingCentre(ArrayList<Product> list, User user) {
        this.listOfProducts = list;
        this.currentUser = user;
        cart = new ShoppingCart();

        JFrame jFrame = new JFrame("Westminster Shopping Centre");
        jFrame.setSize(610, 550);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        selectCategory.setBounds(80, 20, 160, 25);
        jPanel.add(selectCategory);

        shoppingCart.setBounds(430, 10, 150, 25);
        shoppingCart.addActionListener(this);
        jPanel.add(shoppingCart);

        addToShoppingCart.setBounds(200, 470, 170, 25);
        addToShoppingCart.addActionListener(this);
        jPanel.add(addToShoppingCart);

        categories = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        categories.setSelectedItem("All");
        categories.setBounds(240, 20, 160, 25);
        categories.addActionListener(this);
        jPanel.add(categories);

        productDetails.setFont(new Font("", Font.BOLD, 12));
        productDetails.setBounds(30, 320, 200, 25);
        jPanel.add(productDetails);

        productInfo = new JLabel("");
        productInfo.setBounds(30, 340, 200, 125);
        jPanel.add(productInfo);

        tableModel = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price(Rs.)", "Info"}, 0);
        for (Product products : listOfProducts) {
            Object[] productSet = {products.getProductId(), products.getProductName(), products.getCategory(), products.getPrice(), products.getInfo()};
            tableModel.addRow(productSet);
        }

        productTable = new JTable(tableModel);
        TableColumnModel columnModel = productTable.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(150);
        JScrollPane jScrollPane = new JScrollPane(productTable);
        jScrollPane.setBounds(25, 70, 550, 250);
        jPanel.add(jScrollPane);

        productTable.getSelectionModel().addListSelectionListener(this);

        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    private void shoppingCartFrame() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setSize(600, 450);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Product", "Quantity", "Price"}, 0);

        JScrollPane scrollPane = new JScrollPane(new JTable(model));
        scrollPane.setBounds(10, 10, 580, 200);
        panel.add(scrollPane);

        ArrayList<Product> products = cart.getCart();
        model.setRowCount(0);

        double total = 0;
        boolean threeItems = false;
        boolean firstPurchase = false;
        int electronicsCount = 0;
        int clothingCount = 0;
        double discount = 0;

        for (Product product : products) {
            int quantity = cart.getQuantity(product);

            Object[] arr = {product.getProductId() + ", " + product.getProductName() + ", " + product.getInfo(), quantity, (quantity * product.getPrice())};
            model.addRow(arr);
            total += (quantity * product.getPrice());

            if (product.getCategory().equalsIgnoreCase("Electronics")) {
                electronicsCount += quantity;
            } else if (product.getCategory().equalsIgnoreCase("Clothing")) {
                clothingCount += quantity;
            }

            if (electronicsCount >= 3 || clothingCount >= 3) {
                threeItems = true;
            }

            if (currentUser.getPurchaseCount() < 1) {
                firstPurchase = true;
            }
        }

        JLabel totalL = new JLabel("Total: Rs. " + String.format("%.2f", total));
        totalL.setBounds(400, 250, 200, 30);

        if (threeItems) {
            discount = (total * 0.20);
            JLabel discountLbl20 = new JLabel("Three items in the same Category Discount (20%): -Rs. " + String.format("%.2f", discount));
            discountLbl20.setBounds(150, 280, 400, 25);
            panel.add(discountLbl20);
        }

        if (firstPurchase) {
            discount = (total * 0.10);
            JLabel discountLbl10 = new JLabel("First Purchase Discount (10%) : -Rs. " + String.format("%.2f", discount));
            discountLbl10.setBounds(250, 320, 400, 25);
            panel.add(discountLbl10);
        }

        JLabel finalL = new JLabel("Final Total: Rs. " + String.format("%.2f", (total - discount)));
        finalL.setFont(new Font("", Font.BOLD, 12));
        finalL.setBounds(370, 350, 400, 25);

        checkOutBtn = new JButton("Confirm purchase");
        checkOutBtn.addActionListener(this);
        checkOutBtn.setBounds(430, 380, 100, 25);
        checkOutBtn.setFont(new Font("", Font.BOLD, 12));

        panel.add(totalL);
        panel.add(finalL);
        panel.add(checkOutBtn);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Shopping Cart")){
            shoppingCartFrame();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Add to Shopping Cart")){
            int idx = productTable.getSelectedRow();
            if (idx != -1) {
                Product product = listOfProducts.get(idx);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Quantity: "));
                if(listOfProducts.get(idx).getNoItems() > 0 && quantity <= listOfProducts.get(idx).getNoItems()){
                    listOfProducts.get(idx).decreaseQuantity(quantity);
                    cart.addProductToCart(product, quantity);
                    JOptionPane.showMessageDialog(null, product.getProductName()+" Added To Cart!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insufficient Quantity!");
                }
            }
        }

        else if (e.getSource() == checkOutBtn) {
            // Perform checkout actions
            clearShoppingCart();
            incrementUserPurchaseCount();
            JOptionPane.showMessageDialog(null, "Checkout successful!\nYour purchases count has been increased by one.");
        }

        if (e.getActionCommand().equalsIgnoreCase("comboBoxChanged")) {
            String category = (String) categories.getSelectedItem();
            tableModel.setRowCount(0);  // Clear the existing rows

            for (Product product : listOfProducts) {
                if (category.equalsIgnoreCase("All") || product.getCategory().equalsIgnoreCase(category)) {
                    Object[] arr = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice()};
                    tableModel.addRow(arr);
                }
            }
        }
    }

    private void clearShoppingCart() {
        cart.clearCart();
    }

    private void incrementUserPurchaseCount() {
        currentUser.increasePurchaseCount();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = listOfProducts.get(selectedRow);
            String productDetails = generateProductDetails(selectedProduct);
            productInfo.setText(productDetails);
        }
    }

    private String generateProductDetails(Product product) {
        String category = product.getCategory();
        StringBuilder stringBuilder = new StringBuilder("<html>"
                + "<b>Product ID:</b> " + product.getProductId() + "<br/>"
                + "<b>Name:</b> " + product.getProductName() + "<br/>"
                + "<b>Category:</b> " + category + "<br/>"
                + "<b>Price:</b> £" + product.getPrice() + "<br/>");

        if (category.equalsIgnoreCase("Electronics")) {
            Electronics electronics = (Electronics) product;
            stringBuilder.append("<b>Brand:</b> ").append(electronics.getBrand()).append("<br/>")
                    .append("<b>Warranty Period:</b> ")
                    .append(electronics.getWarrantyPeriod()).append("<br/>");
        } else if (category.equalsIgnoreCase("Clothing")) {
            Clothing c = (Clothing) product;
            stringBuilder.append("<b>Size:</b> ").append(c.getSize()).append("<br/>")
                    .append("<b>Colour:</b> ")
                    .append(c.getColor()).append("<br/>");
        }
        stringBuilder.append("<b>Items Available:</b> ").append(product.getNoItems())
                .append("</html>");
        return stringBuilder.toString();
    }
}




