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
 * Created at: 30/03/2008 - 18:53:54
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
 * Criado em: 30/03/2008 - 18:53:54
 * 
 */

package org.jrimum.vallia.digitoverificador;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste da classe CPFDV.
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a> 
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since JRimum 1.0
 * 
 * @version 1.0
 */
public class TestCPFDV{

	private CPFDV dvCPF;

	@Before
	public void setUp() throws Exception {

		dvCPF = new CPFDV();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroNullDisparaExcecao() {
		dvCPF.calcule(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroEmBrancoDisparaExcecao() {
		dvCPF.calcule("  ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoStringComLetrasDisparaExcecao() {
		dvCPF.calcule("1A2B3C");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroApenasZerosDisparaExcecao() {
		dvCPF.calcule("000000000");
	}
	
	@Test
	public void quandoNumeroNoFormatoCorretoSemFormatacaoCalculaCorretamente() {
		assertEquals(38, dvCPF.calcule("222333666"));
	}

	@Test
	public void quandoNumeroNoFormatoCorretoComFormatacaoCalculaCorretamente() {
		assertEquals(38, dvCPF.calcule("222.333.666"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void quandoNumeroLongMaiorQue9DigitosDisparaExcecao() {
		dvCPF.calcule(2223336661L);
	}

	@Test
	public void quandoNumeroLongMenorQue9DigitosCalculaCorretamente() {

		assertEquals(38, dvCPF.calcule(222333666L));
		assertEquals(87, dvCPF.calcule(2333666L));
	}
}
