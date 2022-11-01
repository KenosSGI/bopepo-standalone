/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 03/08/2008 - 12:27:56
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 03/08/2008 - 12:27:56
 * 
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.08.03 at 12:27:56 AM GMT-03:00 
//

package org.jrimum.texgit.language;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.jrimum.texgit.type.component.Side;

/**
 * 
 * 
 * <p>
 * Instrumento utilizado para preencher um field com um formato e caracteres pr�
 * definidos.
 * </p>
 * 
 * 
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetaFiller")
public class MetaFiller {

	@XmlAttribute(required = true)
	protected String padding;
	@XmlAttribute
	protected EnumSide sideToFill;

	/**
	 * Gets the value of the padding property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPadding() {
		return padding;
	}

	/**
	 * Sets the value of the padding property.
	 * 
	 * @param value value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPadding(String value) {
		this.padding = value;
	}

	/**
	 * Gets the value of the sideToFill property.
	 * 
	 * @return possible object is {@link Side }
	 * 
	 */
	public EnumSide getSideToFill() {
		if (sideToFill == null) {
			return EnumSide.LEFT;
		} else {
			return sideToFill;
		}
	}

	/**
	 * Sets the value of the sideToFill property.
	 * 
	 * @param value value
	 *            allowed object is {@link Side }
	 * 
	 */
	public void setSideToFill(EnumSide value) {
		this.sideToFill = value;
	}

}
