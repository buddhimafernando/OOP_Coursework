package GUI;

import Console.Product;
import Console.ShoppingCart;

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

public class ShoppingCentre implements ActionListener, ListSelectionListener {

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

    ShoppingCentre(ArrayList<Product> list) {
        this.listOfProducts = list;
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

        // Adding ListSelectionListener to the productTable
        productTable.getSelectionModel().addListSelectionListener(this);

        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("View Cart")){
            shoppingCartFrame();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Add To Cart")){
            int idx = table.getSelectedRow();
            if (idx != -1) {
                Product product = products.get(idx);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Quantity: "));
                if(products.get(idx).getQuantity() > 0 && quantity <= products.get(idx).getQuantity()){
                    products.get(idx).decreaseQuantity(quantity);
                    shoppingCart.addProduct(product, quantity);
                    JOptionPane.showMessageDialog(null, product.getProductName()+" Added To Cart!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insufficient Quantity!");
                }
            }
        }
        if(e.getActionCommand().equalsIgnoreCase("comboBoxChanged")){
            String category = (String) comboBox.getSelectedItem();
            model.setRowCount(0);
            for (Product product : products) {
                if(category.equalsIgnoreCase("All")){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
                else if(product.getProductCategory().equalsIgnoreCase(category)){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
            }
        }
    }

    private void shoppingCartFrame() {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = products.get(selectedRow);
            String productDetails = generateProductDetails(selectedProduct);
            productL.setText(productDetails);
        }
    }

    private String generateProductDetails(Product product) {
        String category = product.getProductCategory();
        StringBuilder stringBuilder = new StringBuilder("<html>"
                + "<b>Product ID:</b> " + product.getProductID() + "<br/>"
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
        stringBuilder.append("<b>Items Available:</b> ").append(product.getQuantity())
                .append("</html>");
        return stringBuilder.toString();
    }
}


    }
}




