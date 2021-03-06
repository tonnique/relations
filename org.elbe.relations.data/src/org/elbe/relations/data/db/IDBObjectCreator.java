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
package org.elbe.relations.data.db;

import java.io.IOException;
import java.util.Collection;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 * Interface for classes providing the SQL statements to create the database
 * objects (tables and indexes) needed for the application.
 * 
 * @author Luthiger
 */
public interface IDBObjectCreator {

	/**
	 * Returns the SQL statements based on the specified database model.
	 * 
	 * @param inXMLName
	 *            String name of the XML file specifying the database model.
	 * @return Collection<String> of SQL CREATE TABLE/INDEX statements
	 * @throws IOException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	Collection<String> getCreateStatemens(String inXMLName) throws IOException,
	        TransformerFactoryConfigurationError, TransformerException;

}
