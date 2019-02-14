package utils;

import java.util.Comparator;

import order.Product;

public class ProductComparator  implements Comparator<Product>{

	@Override
	public int compare(Product arg0, Product arg1) {
		return arg0.getId().compareTo(arg1.getId());
	}

}
