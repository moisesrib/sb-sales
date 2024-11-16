package com.sb.util;

public class ProductQueries {
    public static final String FIND_BY_ID = "SELECT * FROM products WHERE (barcode = ? OR name = ?) AND deleted_at IS NULL";
}
