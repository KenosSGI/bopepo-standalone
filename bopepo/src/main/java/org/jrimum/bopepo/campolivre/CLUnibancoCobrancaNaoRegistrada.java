package org.jrimum.bopepo.campolivre;

import org.apache.commons.lang.StringUtils;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;
import org.jrimum.utilix.text.Strings;

/**
 * 
 * <p>
 * Representação do campo livre usado para boletos com carteiras (<em>cobrança</em>)
 * sem registro.
 * </p>
 * 
 * Layout:
 * <div>
 * <p>
 * Cobrança Especial (sem registro)
 * </p>
 * 
 * <table>
 * <caption>table</caption>
 * <tr>
 * <td><strong>Posição</strong></td>
 * <td><strong>Campo Livre No Código De
 * Barras (20 a 44)</strong></td>
 * <tr>
 * <td>20</td>
 * 
 * <td>Código da transação = 5</td>
 * </tr>
 * <tr>
 * <td>21 a 26</td>
 * <td>Número do Cliente (Espécie de conta)</td>
 * </tr>
 * <tr>
 * <td>27</td>
 * <td>Dígito Verificador do Número do Cliente</td>
 * </tr>
 * <tr>
 * <td>28 a 29</td>
 * <td>zeros</td>
 * </tr>
 * <tr>
 * <td>30 a 43</td>
 * <td>Referência do Cliente (Nosso Número Gerado Pelo
 * Cliente)</td>
 * </tr>
 * <tr>
 * <td>44</td>
 * <td>Dígito Verificador da Referência do Cliente</td>
 * </tr>
 * </table> </div>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

class CLUnibancoCobrancaNaoRegistrada extends AbstractCLUnibanco {

	/**
	 * 
	 */
	private static final long serialVersionUID = 487906631678160993L;

	/**
	 * 
	 */
	private static final Integer FIELDS_LENGTH = 6;

	private static final Integer CODIGO_TRANSACAO = 5;

	private static final Integer RESERVADO = 0;
	
	/**
	 * <p>
	 *   Dado um título, cria um campo livre para o padrão do Banco Unibanco
	 *   que tenha o tipo de cobrança não registrada.
	 * </p>
	 * @param titulo título com as informações para geração do campo livre titulo título com as informações para geração do campo livre
	 */
	CLUnibancoCobrancaNaoRegistrada(Titulo titulo) {
		super(FIELDS_LENGTH);

		ContaBancaria conta = titulo.getContaBancaria();
		
		Objects.checkNotNull(conta,"Conta Bancária NULA!");
		Objects.checkNotNull(conta.getNumeroDaConta(),"Número da Conta Bancária NULO!");
		Objects.checkNotNull(conta.getNumeroDaConta().getCodigoDaConta(),"Código da Conta Bancária NULO!");
		Objects.checkNotNull(conta.getNumeroDaConta().getDigitoDaConta(),"Dígito da Conta Bancária NULO!");
		Objects.checkNotNull(titulo.getNossoNumero(),"Nosso Número NULO!");
		
		this.add(new FixedField<Integer>(CODIGO_TRANSACAO, 1));

		if(conta.getNumeroDaConta().getCodigoDaConta() > 0){
			
			this.add(new FixedField<Integer>(conta.getNumeroDaConta().getCodigoDaConta(), 6, Fillers.ZERO_LEFT));
			
		}else{
			throw new CampoLivreException(new IllegalArgumentException("Conta bancária com valor inválido, a conta deve ser um número inteiro positivo, e não: "+conta.getNumeroDaConta().getCodigoDaConta()));
		}
		
		if(StringUtils.isNumeric(conta.getNumeroDaConta().getDigitoDaConta())){
			
			Integer digitoDaConta = Integer.valueOf(conta.getNumeroDaConta().getDigitoDaConta());  
			
			if(digitoDaConta >= 0){
				
				this.add(new FixedField<Integer>(Integer.valueOf(digitoDaConta), 1));
			}else{
				
				throw new CampoLivreException(new IllegalArgumentException("O dígito da conta deve ser um número inteiro não-negativo, e não: ["+conta.getNumeroDaConta().getDigitoDaConta()+"]"));
			}
			
		}else{
			
			throw new CampoLivreException(new IllegalArgumentException("O dígito da conta deve ser numérico, e não: ["+conta.getNumeroDaConta().getDigitoDaConta()+"]"));
		}
		
		this.add(new FixedField<Integer>(RESERVADO, 2, Fillers.ZERO_LEFT));
		
		if(StringUtils.isNumeric(titulo.getNossoNumero())){
			
			if(Long.valueOf(Strings.removeStartWithZeros(titulo.getNossoNumero()))>0){
				
				this.add(new FixedField<String>(titulo.getNossoNumero(), 14,Fillers.ZERO_LEFT));
				
			}else{
				
				throw new CampoLivreException(new IllegalArgumentException("O campo (nosso número) do título deve ser um número inteiro positivo, e não: ["+titulo.getNossoNumero()+"]"));
			}
		}else{
			
			throw new CampoLivreException(new IllegalArgumentException("O campo (nosso número) do título deve ser numérico, e não: ["+titulo.getNossoNumero()+"]"));			
		}
		
		this.add(new FixedField<String>(calculeDigitoEmModulo11(titulo.getNossoNumero()), 1));			
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
