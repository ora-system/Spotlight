/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package au.com.spotlight.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import au.com.spotlight.core.constants.SpotlightCoreConstants;
import au.com.spotlight.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
public class SpotlightCoreManager extends GeneratedSpotlightCoreManager
{
	public static final SpotlightCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SpotlightCoreManager) em.getExtension(SpotlightCoreConstants.EXTENSIONNAME);
	}
}
