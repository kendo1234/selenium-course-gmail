package com.appsenseca.Categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public interface Critical {
}