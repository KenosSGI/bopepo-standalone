package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;

/**
 * <p>
 * Teste unitário do campo livre do Banco Intermedium.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:fernandobgi@gmail.com">Fernando Dias</a>
 *  
 */
public class TestCLBancoIntermedium extends AbstractCampoLivreBaseTest<CLBancoIntermedium> {

	@Before
	public void setUp(){

		titulo.getContaBancaria().setBanco(BancosSuportados.BANCO_INTEMEDIUM.create());
		titulo.getContaBancaria().setAgencia( new Agencia(1, "9"));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(123555));
		titulo.getContaBancaria().setCarteira(new Carteira(112));
		titulo.setNossoNumero("0054882025");
		titulo.setDigitoDoNossoNumero("1");

		createCampoLivreToTest();

		setCampoLivreEsperadoComoString("0001112012355500548820251");
	}
}
