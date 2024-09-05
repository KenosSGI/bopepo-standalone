/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 16/04/2008 - 23:09:08
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 16/04/2008 - 23:09:08
 * 
 */

package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;

/**
 * Monta o Campo Livre do código de barras.
 *
 * <p>Posições e Tamanhos dos Campos:
 * <ul>
 *   <li>Código do Cedente - Tamanho 12: Código do cedente (Descrição D007).</li>
 *   <li>Nosso Número - Tamanho 10: Nosso número do título sem o dígito verificador (Descrição D017).</li>
 *   <li>Código da Carteira - Tamanho 2: Código da Carteira (Descrição D019).</li>
 *   <li>Identificador de Layout - Tamanho 1: Identificador de layout.
 *     <ul>
 *       <li>3 para cobrança registrada com emissão pelo banco.</li>
 *       <li>4 para cobrança direta com emissão pelo cedente.</li>
 *     </ul>
 *   </li>
 * </ul>
 * 
 * @author Ricardo Santana <rsantana@kenos.com.br>
 * 
 * @since 0.4.6
 * 
 * @version 0.4.6
 */
class CLC6Bank extends AbstractCLC6Bank {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 9086708201366545967L;
	
	/**
	 * Tamanho deste campo. Em outras palavras, é a quantidade de partes que
	 * compõem este campo livre.
	 */
	private static final Integer FIELDS_LENGTH = 4;

	/**
	 * <p>
	 *   Dado um título, cria o campo livre padrão do Banco C6.
	 * </p>
	 * @param titulo título com as informações para geração do campo livre titulo título com as informações para geração do campo livre
	 */
	public CLC6Bank(Titulo titulo) {
		super(FIELDS_LENGTH);
		
		ContaBancaria conta = titulo.getContaBancaria();
		
		this.add(new FixedField<Integer>(conta.getNumeroDaConta().getCodigoDaConta(), 12, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(titulo.getNossoNumero(), 10, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(conta.getCarteira().getCodigo(), 2, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(4, 1, Fillers.ZERO_LEFT));
	}
	
	@Override
	protected void addFields(Titulo titulo) {
		// TODO IMPLEMENTAR
		Exceptions.throwUnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
	}

	@Override
	protected void checkValues(Titulo titulo) {
		// TODO IMPLEMENTAR
		Exceptions.throwUnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
	}
}

