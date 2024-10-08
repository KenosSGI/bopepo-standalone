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

import static org.junit.Assert.assertEquals;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Teste unitário do campo livre do banco itaú para carteiras padrão
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 *
 */
public class TestCLC6Bank extends AbstractCampoLivreBaseTest<CLC6Bank> {
	@Before
	public void setUp(){
		titulo.getContaBancaria().setBanco(BancosSuportados.C6_BANK.create());
		titulo.getContaBancaria().setAgencia(new Agencia(1, "1"));
		titulo.getContaBancaria().setCarteira(new Carteira(20));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(12312312, "6"));
		titulo.setNumeroDoDocumento("1234567");
		titulo.setNossoNumero("1234567890");
		
		createCampoLivreToTest();
		
		setCampoLivreEsperadoComoString("0000123123121234567890204");
	}

	@Test
	public void testWriteParaCarteirasQueNaoPrecisamDeContaEAgencia() {
		titulo.getContaBancaria().setCarteira(new Carteira(20));
		
		createCampoLivreToTest();
		
		assertEquals(25, writeCampoLivre().length());
		assertEquals("0000123123121234567890204", writeCampoLivre());
	}
}
