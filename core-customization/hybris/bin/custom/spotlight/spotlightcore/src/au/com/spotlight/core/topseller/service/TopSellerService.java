package au.com.spotlight.core.topseller.service;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * @author SindhuSayana
 */
public interface TopSellerService {

    /**
     * @param baseSiteModel
     * @return
     */
    List<ProductModel> getTopSellingProducts(BaseSiteModel baseSiteModel);

    /**
     * @param productModel
     * @param baseSiteModel
     * @return
     */
    Long getTopQuantityForProduct(final ProductModel productModel,final BaseSiteModel baseSiteModel);
}
