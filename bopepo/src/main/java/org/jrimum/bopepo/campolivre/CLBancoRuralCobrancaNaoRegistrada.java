package org.jrimum.bopepo.campolivre;

import static org.jrimum.bopepo.parametro.ParametroBancoRural.CODIGO_REDUZIDO;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;

/**
 * <p>
 * O campo livre do Banco Rural, para cobrança não registrada, deve seguir esta
 * forma:
 * </p>
 * 
 * <table>
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
 * <td style="text-align:left;padding-left:10">Tipo de Cobrança - 9</td>
 * <td style="text-align:left;padding-left:10">Tipo de Cobrança - 9</td>
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
 * <td>27-41</td>
 * <td>15</td>
 * <td>9(15)</td>
 * <td style="text-align:left;padding-left:10">Seu número</td>
 * <td style="text-align:left;padding-left:10">Nosso número</td>
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
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 */
class CLBancoRuralCobrancaNaoRegistrada extends AbstractCLBancoRural{

	/**
	 * 
	 */
	private static final long serialVersionUID = -837754906530330855L;

	/**
	 * Número de campos.
	 */
	private static final Integer FIELDS_LENGTH = Integer.valueOf(5);
	
	/**
	 * Tipo de Cobrança = 9. 
	 */
	private static final Integer TIPO_COBRANCA = Integer.valueOf(9);
	
	/**
	 * Constante
	 */
	private static final String ZEROS = "000";
	
	/**
	 * <p>
	 * Dado um título, cria um campo livre para cobrança não registrada do Banco
	 * Rural.
	 * </p>
	 * 
	 * @param titulo titulo
	 *            - Título com as informações para geração do campo livre
	 */
	CLBancoRuralCobrancaNaoRegistrada(Titulo titulo) {
		
		super(FIELDS_LENGTH);
		
		this.add( new FixedField<Integer>( TIPO_COBRANCA , 1 ) );
		this.add( new FixedField<Integer>( titulo.getContaBancaria().getAgencia().getCodigo(), 3 , Fillers.ZERO_LEFT ) );
		this.add( new FixedField<Integer>(titulo.getParametrosBancarios().<Integer>getValor(CODIGO_REDUZIDO), 3, Fillers.ZERO_LEFT ) );
		this.add( new FixedField<String>( titulo.getNossoNumero(), 15 , Fillers.ZERO_LEFT ) );
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
