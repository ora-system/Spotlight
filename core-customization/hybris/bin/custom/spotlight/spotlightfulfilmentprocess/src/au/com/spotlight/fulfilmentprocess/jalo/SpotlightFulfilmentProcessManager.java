/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package au.com.spotlight.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import au.com.spotlight.fulfilmentprocess.constants.SpotlightFulfilmentProcessConstants;

public class SpotlightFulfilmentProcessManager extends GeneratedSpotlightFulfilmentProcessManager
{
	public static final SpotlightFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (SpotlightFulfilmentProcessManager) em.getExtension(SpotlightFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
