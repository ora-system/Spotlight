package au.com.spotlight.core.topseller.service.impl;

import au.com.spotlight.core.topseller.dao.TopSellerDao;
import au.com.spotlight.core.topseller.service.TopSellerService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;
import org.apache.log4j.Logger;

import java.util.List;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * @author SindhuSayana
 */
public class DefaultTopSellerService implements TopSellerService {

    private static final Logger LOG =Logger.getLogger(DefaultTopSellerService.class);

    private TopSellerDao topSellerDao;

    /**
     * @param baseSiteModel
     * @return
     */
    @Override
    public List<ProductModel> getTopSellingProducts(BaseSiteModel baseSiteModel) {
        validateParameterNotNull(baseSiteModel, "baseSiteModel must not be null!");
        if(LOG.isDebugEnabled()){
            LOG.debug("Service for TopSeller products");
        }
        return getTopSellerDao().getTopSellingProducts(baseSiteModel);
    }

    public TopSellerDao getTopSellerDao() {
        return topSellerDao;
    }

    public void setTopSellerDao(TopSellerDao topSellerDao) {
        this.topSellerDao = topSellerDao;
    }
}
