/***************************************************************************
 * This package is part of Relations application.
 * Copyright (C) 2004-2013, Benno Luthiger
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ***************************************************************************/
package org.elbe.relations.internal.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Image;
import org.elbe.relations.data.bom.IItem;
import org.elbe.relations.data.utility.UniqueID;
import org.elbe.relations.models.AbstractAssociationsModel;
import org.elbe.relations.models.IAssociationsModel;
import org.elbe.relations.models.ItemAdapter;
import org.hip.kernel.exc.VException;

/**
 * An implementation of <code>IAssociationsModel</code> that can be used if an
 * item is created but not yet saved.
 *
 * @author Luthiger
 */
public class UnsavedAssociationsModel extends AbstractAssociationsModel
        implements IAssociationsModel {

	/**
	 * Factory method, creates an <code>UnsavedAssociationsModel</code>
	 * instance.
	 *
	 * @param inItem
	 *            {@link IItem}
	 * @param inContext
	 *            {@link IEclipseContext}
	 * @param inImage
	 * @return {@link UnsavedAssociationsModel}
	 * @throws VException
	 * @throws SQLException
	 */
	public static UnsavedAssociationsModel createModel(final IItem inItem,
	        final IEclipseContext inContext, final Image inImage)
	                throws VException, SQLException {
		final UnsavedAssociationsModel outModel = ContextInjectionFactory
		        .make(UnsavedAssociationsModel.class, inContext);
		final ItemAdapter lItem = new ItemAdapter(inItem, inImage, inContext);
		outModel.setFocusedItem(lItem);
		outModel.initialize(outModel.getFocusedItem());
		return outModel;
	}

	/**
	 * Factory method, creates an <code>UnsavedAssociationsModel</code>
	 * instance.
	 *
	 * @param inItem
	 *            {@link IItem}
	 * @param inContext
	 *            {@link IEclipseContext}
	 * @param inSelected
	 *            {@link ItemAdapter}
	 * @return {@link UnsavedAssociationsModel}
	 * @throws VException
	 * @throws SQLException
	 */
	public static UnsavedAssociationsModel createModel(final IItem inItem,
	        final IEclipseContext inContext, final ItemAdapter inSelected)
	                throws VException, SQLException {
		final UnsavedAssociationsModel outModel = createModel(inItem, inContext,
		        inSelected.getImage());
		outModel.setSelected(inSelected);
		return outModel;
	}

	/**
	 * This method overrides the super class implementation.
	 *
	 * @param inItem
	 *            ItemAdapter the focus (i.e. central) item.
	 * @throws VException
	 * @throws SQLException
	 */
	@Override
	protected void initialize(final ItemAdapter inItem)
	        throws VException, SQLException {
		related = new ArrayList<ItemAdapter>();
		uniqueIDs = new ArrayList<UniqueID>();

		added = new HashSet<UniqueID>();
		removed = new HashSet<UniqueID>();
	}

	private void setSelected(final ItemAdapter inSelected) throws VException {
		related.add(inSelected);
		final UniqueID lID = new UniqueID(inSelected.getItemType(),
		        inSelected.getID());
		uniqueIDs.add(lID);
		added.add(lID);
	}

	/**
	 * We need the possibility to replace the dummy item with a real item
	 * (having a correct itemID) before we save the associations.
	 *
	 * @param inCenter
	 *            ItemAdapter
	 */
	public void replaceCenter(final ItemAdapter inCenter) {
		setFocusedItem(inCenter);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.elbe.relations.models.AbstractAssociationsModel#afterSave()
	 */
	@Override
	protected void afterSave() throws VException, SQLException {
		// Intentionally left empty.
	}

}
