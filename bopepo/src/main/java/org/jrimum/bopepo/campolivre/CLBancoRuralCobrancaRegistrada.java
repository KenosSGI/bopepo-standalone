package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;

/**
 * <p>
 * O campo livre do Banco Rural, para cobrança registrada, deve seguir esta
 * forma:
 * </p>
 * 
 * <table>
 * 
 * <caption>table</caption>
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
 * <td style="text-align:left;padding-left:10px">Tipo de Cobrança - 0</td>
 * <td style="text-align:left;padding-left:10">Tipo de Cobrança - 0</td>
 * </tr>
 * <tr>
 * <td>21-23</td>
 * <td>3</td>
 * <td>9(3)</td>
 * <td style="text-align:left;padding-left:10">Código da Agência</td>
 * <td style="text-align:left;padding-left:10">Agência Cedente</td>
 * </tr>
 * <tr>
 * <td>24-32</td>
 * <td>9</td>
 * <td>9(9)</td>
 * <td style="text-align:left;padding-left:10">Conta Corrente</td>
 * <td style="text-align:left;padding-left:10">Conta do Cedente</td>
 * </tr>
 * <tr>
 * <td>33-33</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td style="text-align:left;padding-left:10">Dígito da Conta Corrente</td>
 * <td style="text-align:left;padding-left:10">Dígito da Conta do Cedente</td>
 * </tr>
 * <tr>
 * <td>34-40</td>
 * <td>7</td>
 * <td>9(7)</td>
 * <td style="text-align:left;padding-left:10">Nosso Número</td>
 * <td style="text-align:left;padding-left:10">Nosso Número</td>
 * </tr>
 * <tr>
 * <td>41-41</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td style="text-align:left;padding-left:10">Dígito do Nosso Número</td>
 * <td style="text-align:left;padding-left:10">Dígito do Nosso Número</td>
 * </tr>
 * <tr>
 * <td>42-44</td>
 * <td>3</td>
 * <td>9(3)</td>
 * <td style="text-align:left;padding-left:10">Constante "000"</td>
 * <td style="text-align:left;padding-left:10">Zeros</td>
 * </tr>
 * 
 * </table>
 * 
 * @author <a href="mailto:fernandobgi@gmail.com">Fernando Dias</a>
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 */
class CLBancoRuralCobrancaRegistrada extends AbstractCLBancoRural{
	
	private static final long serialVersionUID = -5166628254198207874L;
	
	/**
	 * Número de campos.
	 */
	private static final Integer FIELDS_LENGTH = Integer.valueOf(7);
	
	/**
	 * Tipo de Cobrança = 0. 
	 */
	private static final Integer TIPO_COBRANCA = Integer.valueOf(0);
	
	/**
	 * Constante
	 */
	private static final String ZEROS = "000";

	/**
	 * <p>
	 * Dado um título, cria um campo livre para cobrança registrada do Banco
	 * Rural.
	 * </p>
	 * 
	 * @param titulo titulo
	 *            - título com as informações para geração do campo livre
	 */
	CLBancoRuralCobrancaRegistrada(Titulo titulo) {
		
		super(FIELDS_LENGTH);
		
		this.add( new FixedField<Integer>( TIPO_COBRANCA , 1 ) );
		this.add( new FixedField<Integer>( titulo.getContaBancaria().getAgencia().getCodigo(), 3 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<Integer>( titulo.getContaBancaria().getNumeroDaConta().getCodigoDaConta(), 9, Fillers.ZERO_LEFT ) );
		this.add( new FixedField<String>( titulo.getContaBancaria().getNumeroDaConta().getDigitoDaConta(), 1 ) );
		this.add( new FixedField<String>( titulo.getNossoNumero(), 7 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<String>( titulo.getDigitoDoNossoNumero(), 1 ) );
		this.add( new FixedField<String>( ZEROS, 3));
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
