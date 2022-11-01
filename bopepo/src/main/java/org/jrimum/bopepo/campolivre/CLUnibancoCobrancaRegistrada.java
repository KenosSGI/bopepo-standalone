package org.jrimum.bopepo.campolivre;

import static org.jrimum.utilix.text.DateFormat.YYMMDD;

import java.util.Date;

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
 * com registro.
 * </p>
 * 
 * Layout:
 * <p>
 * Cobrança Direta (com registro)
 * </p>
 * 
 * <table>
 * <caption>table</caption>
 * <tr>
 * <td><strong>Posição</strong></td>
 * <td><strong>Campo Livre No Código De
 * Barras (20 a 44)</strong></td>
 * <tr>
 * <td>20 a 21</td>
 * <td>Código da transação = 04</td>
 * 
 * </tr>
 * <tr>
 * <td>22 a 27</td>
 * <td>Data do Vencimento do Título (AAMMDD)</td>
 * </tr>
 * <tr>
 * <td>28 a 31</td>
 * 
 * <td>Agência do Cedente</td>
 * </tr>
 * <tr>
 * <td>32</td>
 * 
 * <td>Dígito Verificador da Agência do Cedente</td>
 * </tr>
 * <tr>
 * <td>33 a 43</td>
 * <td>Nosso Número</td>
 * </tr>
 * <tr>
 * 
 * <td>44</td>
 * <td>Super Digito do Nosso Número (*)</td>
 * </tr>
 * </table>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

class CLUnibancoCobrancaRegistrada extends AbstractCLUnibanco {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2740172688796212239L;

	/**
	 * 
	 */
	private static final Integer FIELDS_LENGTH = 6;

	private static final String CODIGO_TRANSACAO = "04";
	
	/**
	 * <p>
	 *   Dado um título, cria um campo livre para o padrão do Banco Unibanco
	 *   que tenha o tipo de cobrança registrada.
	 * </p>
	 * @param titulo título com as informações para geração do campo livre titulo título com as informações para geração do campo livre
	 */
	CLUnibancoCobrancaRegistrada(Titulo titulo) {
		super(FIELDS_LENGTH);
		
		ContaBancaria conta = titulo.getContaBancaria();
		
		Objects.checkNotNull(conta,"Conta Bancária NULA!");
		Objects.checkNotNull(titulo.getDataDoVencimento(), "Data de vencimento do título NULA!");
		Objects.checkNotNull(conta.getAgencia().getCodigo(), "Número da Agência Bancária NULO!");
		Objects.checkNotNull(conta.getAgencia().getDigitoVerificador(),"Dígito da Agência Bancária NULO!");
		Objects.checkNotNull(titulo.getNossoNumero(),"Nosso Número NULO!");
		
		this.add(new FixedField<String>(CODIGO_TRANSACAO, 2));
		this.add(new FixedField<Date>(titulo.getDataDoVencimento(), 6, YYMMDD.copy()));
			
		if(conta.getAgencia().getCodigo() > 0){
			
			this.add(new FixedField<Integer>(conta.getAgencia().getCodigo(), 4, Fillers.ZERO_LEFT));
			
		}else{
			
			throw new CampoLivreException(new IllegalArgumentException("Agência bancária com valor inválido, a agência deve ser um número inteiro positivo, e não: "+conta.getNumeroDaConta().getCodigoDaConta()));
		}
		
		
		if (StringUtils.isNumeric(conta.getAgencia().getDigitoVerificador())) {
			
			Integer digitoDaAgencia = Integer.valueOf(conta.getAgencia().getDigitoVerificador());  
			
			if(digitoDaAgencia>=0){
				
				this.add(new FixedField<Integer>(Integer.valueOf(digitoDaAgencia), 1));
			}else{
				
				throw new CampoLivreException(new IllegalArgumentException("O dígito da agência deve ser um número interio não-negativo, e não: ["+conta.getAgencia().getDigitoVerificador()+"]"));
			}
		}else{
			
			throw new CampoLivreException(new IllegalArgumentException("O dígito da agência deve ser numérico, e não: ["+conta.getAgencia().getDigitoVerificador()+"]"));
		}
		
		if(StringUtils.isNumeric(titulo.getNossoNumero())){
			
			if(Long.valueOf(Strings.removeStartWithZeros(titulo.getNossoNumero()))>0){
				
				this.add(new FixedField<String>(titulo.getNossoNumero(), 11,Fillers.ZERO_LEFT));
			}else{
				
				throw new CampoLivreException(new IllegalArgumentException("O campo (nosso número) do título deve ser um número natural positivo, e não: ["+titulo.getNossoNumero()+"]"));
			}
		}else{
			
			throw new CampoLivreException(new IllegalArgumentException("O campo (nosso número) do título deve ser numérico, e não: ["+titulo.getNossoNumero()+"]"));
		}
		
		this.add(new FixedField<String>(calculeSuperDigito(titulo.getNossoNumero()), 1));
	}

	/**
	 * <p>
	 * Calcula o Super Dígito do Nosso Número.
	 * </p>
	 * 
	 * <p>
	 * Super dígito do “Nosso Número” [calculado com o MÓDULO 11 (de 2 a 9)]
	 * obtido utilizando-se os algarismos do “Nosso Número” acrescido do número
	 * 1 à esquerda = [1/NNNNNNNNNNN] e multiplicando-se a sequência obetem-se a
	 * soma dos produtos. Em seguida multiplicando-se novamente a soma por 10 e
	 * depois realizando-se o módulo 11.
	 * </p>
	 * 
	 * 
	 * @param nossoNumero nossoNumero
	 * 
	 * @return Dígito verficador calculado
	 * 
	 * @see #calculeDigitoEmModulo11(String)
	 * @see org.jrimum.vallia.digitoverificador.Modulo
	 * 
	 * @since 0.2
	 */
	private String calculeSuperDigito(String nossoNumero) {

		return calculeDigitoEmModulo11("1" + nossoNumero);
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
