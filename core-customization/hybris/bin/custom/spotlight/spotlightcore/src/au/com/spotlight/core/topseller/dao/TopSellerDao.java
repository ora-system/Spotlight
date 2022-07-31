package au.com.spotlight.core.topseller.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * @author SindhuSayana
 */
public interface TopSellerDao {

    /**
     * @param baseSiteModel
     * @return
     */
    List<ProductModel> getTopSellingProducts(BaseSiteModel baseSiteModel);

}
