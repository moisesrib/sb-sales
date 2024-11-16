package com.sb.view;

import com.sb.controller.ProductController;
import com.sb.model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tela extends JFrame {

    private JPanel contentSearch;
    private JPanel contentTable;
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private ProductController productController;

    public Tela() {
        productController = new ProductController(); // Inicializa o controlador

        setTitle("Tela de vendas");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"Código de Barras", "Nome do Produto", "Preço", "Quantidade"};
        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);

        // Configuração do filtro de pesquisa
        rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);

        // Configuração do campo de busca
        searchField = new JTextField();
        searchField.setHorizontalAlignment(SwingConstants.LEFT);
        searchField.setPreferredSize(new Dimension(820, 30));

        // Adiciona o KeyListener para buscar no banco
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String barcode = searchField.getText().trim();
                    buscarProduto(barcode);
                }
            }
        });

        // Painel de cabeçalho com campo de busca
        contentSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contentSearch.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentSearch.add(new JLabel("Buscar produto:"));
        contentSearch.add(searchField);

        // Painel com margens ao redor da tabela
        contentTable = new JPanel(new BorderLayout());
        contentTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentTable.add(new JScrollPane(table), BorderLayout.CENTER);

        // Adicionando os componentes ao layout da janela principal
        add(contentSearch, BorderLayout.NORTH);
        add(contentTable, BorderLayout.CENTER);
    }

    // Método para buscar produto e atualizar a tabela
    private void buscarProduto(String barcode) {
        if (barcode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um código de barras!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = productController.getProductByBarcode(barcode);
//      Limpar tabela;
//      tableModel.setRowCount(0);

        if (product != null) {
            tableModel.addRow(new Object[]{
                    product.getBarcode(),
                    product.getName(),
                    String.format("%.2f", product.getPrice()),
                    product.getQuantity()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        searchField.setText("");
        searchField.requestFocus();
    }
}
