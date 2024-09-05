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
 * Calcula o Dígito Verificador do Código de Barras.
 *
 * <p>Posições e Tamanhos dos Campos:
 * <ul>
 *   <li>Posição 20 a 23 - Tamanho 4: Agência Beneficiária (Sem o dígito verificador, completar com zeros à esquerda quando necessário).</li>
 *   <li>Posição 24 a 25 - Tamanho 2: Carteira.</li>
 *   <li>Posição 26 a 36 - Tamanho 11: Número do Nosso Número (Sem o dígito verificador).</li>
 *   <li>Posição 37 a 43 - Tamanho 7: Conta do Beneficiário (Sem o dígito verificador, completar com zeros à esquerda quando necessário).</li>
 *   <li>Posição 44 a 44 - Tamanho 1: Zero.</li>
 * </ul>
 *
 * <p>Para o cálculo do Dígito Verificador do Código de Barras, proceder da seguinte forma:
 * <ul>
 *   <li>Cálculo através do módulo 11, com base de cálculo igual a 9.</li>
 * </ul>
 * 
 * @author Ricardo Santana <rsantana@kenos.com.br>
 * 
 * @since 0.4.6
 * 
 * @version 0.4.6
 */
class CLBancoMoneyPlus extends AbstractCLBancoMoneyPlus {

	/**
	 *	Serial ID 
	 */
	private static final long serialVersionUID = 3424792920177341123L;
	
	/**
	 * Tamanho deste campo. Em outras palavras, é a quantidade de partes que
	 * compõem este campo livre.
	 */
	private static final Integer FIELDS_LENGTH = 5;

	/**
	 * <p>
	 *   Dado um título, cria o campo livre padrão do Banco BMP.
	 * </p>
	 * @param titulo título com as informações para geração do campo livre titulo título com as informações para geração do campo livre
	 */
	public CLBancoMoneyPlus(Titulo titulo) {
		super(FIELDS_LENGTH);
		
		ContaBancaria conta = titulo.getContaBancaria();
		
		this.add(new FixedField<Integer>(conta.getAgencia().getCodigo(), 4, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(conta.getCarteira().getCodigo(), 2, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(titulo.getNossoNumero(), 11, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(conta.getNumeroDaConta().getCodigoDaConta(), 7, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>("0", 1, Fillers.ZERO_LEFT));
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

