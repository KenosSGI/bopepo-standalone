package org.jrimum.bopepo.campolivre;

import static org.jrimum.bopepo.parametro.ParametroBancoRural.CODIGO_REDUZIDO;
import static org.jrimum.bopepo.parametro.ParametroBancoRural.VALOR_IOS;

import java.math.BigDecimal;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.text.DecimalFormat;

/**
 * <p>
 * O campo livre do Banco Rural, para cobrança sem registro (apólice de seguro
 * com I.O.S.), deve seguir esta forma:
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
 * <td style="text-align:left;padding-left:10px">Tipo de Cobrança - 4</td>
 * <td style="text-align:left;padding-left:10">Tipo de Cobrança - 4</td>
 * </tr>
 * <tr>
 * <td>21-23</td>
 * <td>3</td>
 * <td>9(3)</td>
 * <td style="text-align:left;padding-left:10">Código da Agência</td>
 * <td style="text-align:left;padding-left:10">Agência Cedente</td>
 * </tr>
 * <tr>
 * <td>24-26</td>
 * <td>3</td>
 * <td>9(3)</td>
 * <td style="text-align:left;padding-left:10">Código Reduzido do Cliente</td>
 * <td style="text-align:left;padding-left:10">O código reduzido deverá ser
 * solicitado ao gerente da agência.</td>
 * </tr>
 * <tr>
 * <td>27-36</td>
 * <td>10</td>
 * <td>9(10)</td>
 * <td style="text-align:left;padding-left:10">Seu número</td>
 * <td style="text-align:left;padding-left:10">Nosso número</td>
 * </tr>
 * <tr>
 * <td>37-37</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td style="text-align:left;padding-left:10">Dígito seu Número</td>
 * <td style="text-align:left;padding-left:10">Dígito nosso número</td>
 * </tr>
 * <tr>
 * <td>42-44</td>
 * <td>3</td>
 * <td>9(3)</td>
 * <td style="text-align:left;padding-left:10">Valor I.O.S. 05 (cinco) inteiros,
 * 02 (duas) decimais</td>
 * <td style="text-align:left;padding-left:10">Valor do imposto</td>
 * </tr>
 * 
 * </table>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 */
class CLBancoRuralCobrancaNaoRegistradaSeguradora extends AbstractCLBancoRural{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2427800425370567806L;

	/**
	 * Número de campos.
	 */
	private static final Integer FIELDS_LENGTH = Integer.valueOf(6);
	
	/**
	 * Tipo de Cobrança = 4. 
	 */
	private static final Integer TIPO_COBRANCA = Integer.valueOf(4);
	
	/**
	 * <p>
	 * Dado um título, cria um campo livre para cobrança sem registro – apólice de seguro com I.O.S..
	 * </p>
	 * 
	 * @param titulo titulo
	 *            - título com as informações para geração do campo livre
	 */
	CLBancoRuralCobrancaNaoRegistradaSeguradora(Titulo titulo) {
		
		super(FIELDS_LENGTH);
		
		this.add( new FixedField<Integer>( TIPO_COBRANCA , 1 ) );
		this.add( new FixedField<Integer>( titulo.getContaBancaria().getAgencia().getCodigo(), 3 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<Integer>(titulo.getParametrosBancarios().<Integer>getValor(CODIGO_REDUZIDO), 3, Fillers.ZERO_LEFT ) );
		this.add( new FixedField<String>( titulo.getNossoNumero(), 10 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<String>( titulo.getDigitoDoNossoNumero(), 1 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<BigDecimal>(titulo.getParametrosBancarios().<BigDecimal>getValor(VALOR_IOS), 7, DecimalFormat.NUMBER_DD_BR.copy(), Fillers.ZERO_LEFT));
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
