/*
 * Copyright 2007, JMatryx Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007, Grupo JMatryx
 * 
 * Licenciado sob a licença da Apache, versão 2.0 (a “licença”); você não pode
 * usar este arquivo exceto na conformidade com a licença. Você pode obter uma
 * cópia da licença em
 * 
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">
 * http://www.apache.org/licenses/LICENSE-2.0 </a>
 * 
 * A menos que seja requerido pela aplicação da lei ou esteja de acordo com a
 * escrita, o software distribuído sob esta licença é distribuído “COMO É”
 * BASE,SEM AS GARANTIAS OU às CONDIÇÕES DO TIPO, expresso ou implicado. Veja a
 * licença para as permissões sobre a línguagem específica e limitações quando
 * sob licença.
 * 
 * 
 * Created at / Criado em : 18/03/2007 - 12:45:07
 * 
 */

package br.com.nordestefomento.jrimum.bopepo.campolivre;

import br.com.nordestefomento.jrimum.domkee.entity.ContaBancaria;
import br.com.nordestefomento.jrimum.domkee.entity.Titulo;
import br.com.nordestefomento.jrimum.utilix.Field;
import br.com.nordestefomento.jrimum.utilix.Filler;

/**
 * 
 * O campo livre do bradesco deve seguir esta forma:
 * 
 * <table border="1" cellpadding="0" cellspacing="0" style="border-collapse:
 * collapse" bordercolor="#111111" width="60%" id="campolivre">
 * <tr> <thead>
 * <th >Posição </th>
 * <th >Tamanho</th>
 * <th >Picture</th>
 * <th >Conteúdo</th>
 * </thead> </tr>
 * <tr>
 * <td >20-23</td>
 * <td >4</td>
 * <td >9(4) </td>
 * <td >Código da Agência (sem dígito)</td>
 * </tr>
 * <tr>
 * <td >24-25</td>
 * <td >2</td>
 * <td >9(2) </td>
 * <td >Código da Carteira</td>
 * </tr>
 * <tr>
 * <td >26-36</td>
 * <td >11</td>
 * <td >&nbsp;9(11) </td>
 * <td >Nosso Número (sem dígito)</td>
 * </tr>
 * <tr>
 * <td >37-43</td>
 * <td >7</td>
 * <td >&nbsp;9(7) </td>
 * <td >Conta do Cedente (sem dígito)</td>
 * </tr>
 * <tr>
 * <td >44-44</td>
 * <td >1</td>
 * <td >9 </td>
 * <td >Zero Fixo</td>
 * </tr>
 * </table>
 * 
 * 
 * @see br.com.nordestefomento.jrimum.bopepo.campolivre.ACampoLivre
 * 
 * @author Gabriel Guimarães
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto 
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since JMatryx 1.0
 * 
 * @version 1.0
 */
class CLBradesco extends ACLBradesco {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1253549781074159862L;

	/**
	 * 
	 */
	private static final Integer FIELDS_LENGTH = 5;
	
	/**
	 * @param fieldsLength
	 * @param stringLength
	 */
	protected CLBradesco(Integer fieldsLength, Integer stringLength) {
		super(fieldsLength, stringLength);
		
	}

	/**
	 * @param titulo
	 * @return
	 */
	static ICampoLivre getInstance(Titulo titulo) {
		
		ACampoLivre clBradesco = new CLBradesco(FIELDS_LENGTH,STRING_LENGTH);
		
		//TODO Código em teste
		ContaBancaria conta = titulo.getCedente().getContasBancarias().iterator().next();
		String nossoNumero = titulo.getNossoNumero();
		
		//TODO Código em teste
		clBradesco.add(new Field<Integer>(conta.getAgencia().getCodigoDaAgencia(), 4, Filler.ZERO_LEFT));
		
		clBradesco.add(new Field<Integer>(conta.getCodigoDaCarteira(), 2, Filler.ZERO_LEFT));
		clBradesco.add(new Field<String>(nossoNumero, 11, Filler.ZERO_LEFT));
		
		//TODO Código em teste
		clBradesco.add(new Field<Integer>(conta.getNumeroDaConta().getCodigoDaConta(), 7, Filler.ZERO_LEFT));
		
		clBradesco.add(new Field<Integer>(0, 1));

		return clBradesco;
	}
}
