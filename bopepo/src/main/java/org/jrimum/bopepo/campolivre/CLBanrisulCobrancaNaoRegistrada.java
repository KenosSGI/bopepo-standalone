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
 * Created at: 19/07/2008 - 11:15:33
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
 * Criado em: 19/07/2008 - 11:15:33
 * 
 */
package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;

/**
 * <p>
 * O campo livre do Barisul para cobrança não registrada (Cobrança Direta
 * "sem registro" - Sistema BDL/Carteira de Letras) deve seguir esta forma:
 * </p>
 * 
 * <table>
 * <caption>table</caption>
 * 
 * <tr>
 * <th>Posição</th>
 * <th>Tamanho</th>
 * <th>Picture</th>
 * <th>Conteúdo (terminologia padrão)</th>
 * <th>Conteúdo (terminologia do banco)</th>
 * </tr>
 *  
 * <tr>
 * <td>20-20</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td style="text-align:left;padding-left:10px">Tipo de Cobrança: constante = 2
 * </td>
 * <td style="text-align:left;padding-left:10">"2" Cobrança Direta, Fichário
 * emitido pelo CLIENTE.</td>
 * </tr>
 * <tr>
 * <td>21-21</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td style="text-align:left;padding-left:10">Constante "1"</td>
 * <td style="text-align:left;padding-left:10">Constante "1"</td>
 * </tr>
 * <tr>
 * <td>22-25</td>
 * <td>4</td>
 * <td>9(4)</td>
 * <td style="text-align:left;padding-left:10">Código da Agência sem digito
 * verificador</td>
 * <td style="text-align:left;padding-left:10">Código da Agência, com quatro
 * dígitos, sem o Número de Controle.</td>
 * </tr>
 * <tr>
 * <td>26-32</td>
 * <td>7</td>
 * <td>9(7)</td>
 * <td style="text-align:left;padding-left:10">Código do Cedente sem dígito
 * verificador</td>
 * <td style="text-align:left;padding-left:10">Código do Cedente sem Número de
 * Controle.</td>
 * </tr>
 * <tr>
 * <td>33-40</td>
 * <td>8</td>
 * <td>9(8)</td>
 * <td style="text-align:left;padding-left:10">Seu número sem dígito verificador
 * </td>
 * <td style="text-align:left;padding-left:10">Nosso Número sem Número de
 * Controle</td>
 * </tr>
 * <tr>
 * <td>41-42</td>
 * <td>2</td>
 * <td>9(2)</td>
 * <td style="text-align:left;padding-left:10">Constante "40"</td>
 * <td style="text-align:left;padding-left:10">Constante "40"</td>
 * </tr>
 * <tr>
 * <td>43-44</td>
 * <td>2</td>
 * <td>9(2)</td>
 * <td style="text-align:left;padding-left:10">Dois dígitos verificadores
 * calculados com os campos anteriores pelos (módulos 10 e 11)</td>
 * <td style="text-align:left;padding-left:10">Duplo Dígito referente às
 * posições 20 a 42 (módulos 10 e 11)</td>
 * </tr>
 * 
 * </table>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a> Valerio
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
class CLBanrisulCobrancaNaoRegistrada extends AbstractCLBanrisul {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6078207986734440842L;
	
	private static final Integer FIELDS_LENGTH = 7;

	CLBanrisulCobrancaNaoRegistrada(Titulo titulo) {
		super(FIELDS_LENGTH);

		this.add(new FixedField<Integer>(2, 1));
		this.add(new FixedField<String>("1", 1));
		this.add(new FixedField<Integer>(titulo.getContaBancaria().getAgencia().getCodigo(), 4, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(titulo.getContaBancaria().getNumeroDaConta().getCodigoDaConta(), 7, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(Integer.valueOf(titulo.getNossoNumero()), 8, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>("40", 2));

		this.add(new FixedField<String>(calculaDuploDigito(concateneOsCamposExistentesAteOMomento()), 2));

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
