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
 * Created at / Criado em : 17/03/2007 - 17:38:37
 * 
 */

package br.com.nordestefomento.jrimum.bopepo.campolivre;

import org.apache.commons.lang.StringUtils;

import br.com.nordestefomento.jrimum.ACurbitaObject;
import br.com.nordestefomento.jrimum.domkee.entity.Titulo;
import br.com.nordestefomento.jrimum.utilix.Field;
import br.com.nordestefomento.jrimum.utilix.Filler;


/**
 * 
 * Esta classe tem como finalidade encapsular toda a lógica de criação de um
 * campo livre e de fornecer para o pacote
 * <code>br.com.nordestefomento.jrimum.bopepo.campolivre</code> 
 * um único ponto de acesso ao mesmo.
 * 
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
public final class FactoryCampoLivre extends ACurbitaObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8572635342980404937L;

	/**
	 * Devolve um ICampoLivre de acordo com o Banco contido na conta do Cedente. 
	 *  
	 * Caso exista implementação para o banco o retorno terá uma referência não nula.
	 * 
	 * @param titulo
	 * 
	 * @return Uma referência para um ICampoLivre.
	 * @throws NotSuporttedBancoException 
	 * @throws NotSuporttedCampoLivreException 
	 */
	public static ICampoLivre getInstance(Titulo titulo) throws NotSuporttedBancoException, NotSuporttedCampoLivreException {

		return ACampoLivre.getInstance(titulo);
	}
	
	/**
	 * Devolve um ICampoLivre de acordo a partir de uma String.
	 * 
	 * @param strCampoLivre
	 * 
	 * @return Uma referência para um ICampoLivre.
	 * @throws IllegalArgumentException
	 */
	public static ICampoLivre getInstace(String strCampoLivre) throws IllegalArgumentException{
		
		ICampoLivre campoLivre = null;
		
		if (!isNull(strCampoLivre, "strCampoLivre")) {

			if (StringUtils.isNotBlank(strCampoLivre)) {

				strCampoLivre = StringUtils.strip(strCampoLivre); 
				
				if (strCampoLivre.length() == ICampoLivre.STRING_LENGTH) {

					if (StringUtils.remove(strCampoLivre, ' ').length() == ICampoLivre.STRING_LENGTH) {

						if (StringUtils.isNumeric(strCampoLivre)) {

							campoLivre = new ICampoLivre() {

								private static final long serialVersionUID = -7592488081807235080L;

								Field<String> campo = new Field<String>(StringUtils.EMPTY,
										STRING_LENGTH, Filler.ZERO_LEFT);

								@Override
								public void read(String str) {
									campo.read(str);
								}

								@Override
								public String write() {
									return campo.write();
								}
							};
							
							campoLivre.read(strCampoLivre);
							
						}else{
							
							IllegalArgumentException e = new IllegalArgumentException("The strCampoLivre [ " + strCampoLivre + " ] must be a numeric string!");
							
							log.error(StringUtils.EMPTY, e);
							
							throw e;
						}
					}else{
						
						IllegalArgumentException e = new IllegalArgumentException("The strCampoLivre [ " + strCampoLivre + " ] must not contain whitespaces!");
						
						log.error(StringUtils.EMPTY, e);
						
						throw e;
					}
				}else{
					
					IllegalArgumentException e = new IllegalArgumentException("The length strCampoLivre [ " + strCampoLivre + " ] must be equals to 25 not ["+strCampoLivre.length()+"]!");
					
					log.error(StringUtils.EMPTY, e);
					
					throw e;
				}
			}else{
				
				IllegalArgumentException e = new IllegalArgumentException("The strCampoLivre [ " + strCampoLivre + " ] must not be blank!");
				
				log.error(StringUtils.EMPTY, e);
				
				throw e;
			}
		}
		return campoLivre;
	}

}
