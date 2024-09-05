
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
 * Created at: 16/04/2008 - 23:08:17
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
 * Criado em: 16/04/2008 - 23:08:17
 * 
 */
	
package org.jrimum.bopepo.campolivre;
import static org.jrimum.vallia.digitoverificador.Modulo.MOD10;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.vallia.digitoverificador.Modulo;

/**
 *<p>
 * Interface comum para todos os campos livres do Banco BMP que venham a
 * existir.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
abstract class AbstractCLBancoMoneyPlus extends AbstractCampoLivre {

	/**
	 * 	Serial ID
	 */
	private static final long serialVersionUID = -3608437859047183796L;

	/**
	 * <p>Cria um campo livre com um determinado número de campos</p>
	 * 
	 * @see AbstractCampoLivre
	 * 
	 * @param fieldsLength - Número de campos fieldsLength - Número de campos
	 */
	protected AbstractCLBancoMoneyPlus(Integer fieldsLength) {
		
		super(fieldsLength);
	}
	
	protected static CampoLivre create(Titulo titulo){
		
		checkCarteiraNotNull(titulo);
		checkCodigoDaCarteira(titulo);		
		return new CLBancoMoneyPlus(titulo);
	}
	
	/**
	 * <p>
	 * Método auxiliar para calcular o dígito verificador dos campos 31 e 41.
	 * O dígito é calculado com base em um campo fornecido pelos métodos que o chamam
	 * (<code>calculeDigitoDaPosicao31</code> e <code>calculeDigitoDaPosicao41</code>)
	 * </p>
	 * <p>
	 * O cálculo é feito através do módulo 10.
	 * </p>
	 * 
	 * @param campo campo
	 * @return Dígito verificador do campo fornecido.
	 */
	protected Integer calculeDigitoVerificador(String campo) {
				
		int restoDivisao = Modulo.calculeMod10(campo, 1, 2);
		int digito = MOD10 - restoDivisao;
		
		if(digito > 9) {
			digito = 0;
		}
		
		return Integer.valueOf(digito);
	}

}
