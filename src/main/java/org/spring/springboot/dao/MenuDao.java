package org.spring.springboot.dao;


import java.util.List;

import org.spring.springboot.domain.Menu;


public interface MenuDao {
    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(Long userId);

    List<Menu> menuTree();

}
