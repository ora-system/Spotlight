package au.com.spotlight.core.topseller.dao.impl;

import au.com.spotlight.core.topseller.dao.TopSellerDao;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author SindhuSayana
 */
public class DefaultTopSellerDao extends DefaultGenericDao<ProductModel> implements TopSellerDao, Dao {


    private static final String TOP_SELLER_QUERY = "SELECT {" + OrderEntryModel.PRODUCT + "} FROM {" + OrderEntryModel._TYPECODE + "} WHERE {" + OrderEntryModel.CREATIONTIME + "}>=?date " + "GROUP BY {" + OrderEntryModel.PRODUCT + "} ORDER BY MAX({" + OrderEntryModel.QUANTITY + "}) desc";

    public DefaultTopSellerDao(String typecode) {
        super(typecode);
    }

    /**
     * @param baseSiteModel
     * @return
     */
    @Override
    public List<ProductModel> getTopSellingProducts(BaseSiteModel baseSiteModel) {

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -24);//Calculate time before 24 hours from now
        final FlexibleSearchQuery query = new FlexibleSearchQuery(TOP_SELLER_QUERY);
        query.setResultClassList(Collections.singletonList(ProductModel.class));
        query.addQueryParameter("date", cal.getTime());
        query.setCount(100);
        final SearchResult results = getFlexibleSearchService().search(query);
        return results.getResult();
    }
}
