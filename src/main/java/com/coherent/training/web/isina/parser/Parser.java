package com.coherent.training.web.isina.parser;

import com.coherent.training.web.isina.shop.Cart;

import java.io.File;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
