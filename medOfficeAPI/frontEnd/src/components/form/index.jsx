import React, { Component } from 'react';


function Form(){

    const [Nome, setNome] = useState('Digite seu Nome');
    const [Email, setEmail] = useState('Digite seu Email');
    const [lista, setLista] = useState({
        nome: '',
        email: ''
    });
    useEffect(()=>{
      localStorage.setItem('pessoa',JSON.stringify(lista));
    },[lista]);
  

    const addPessoa = useCallback(()=>{
        setLista({
            nome: Nome,
            email: Email
        });
        setNome("");
        setEmail("");
      },[Nome, Email,lista]);

      return (
        <form>
            <label htmlFor="name">Nome</label>
            <input 
                type="text" 
                name="nome"
                id="nome"
                value={nome}
                onChange={this.handleChange}
            />
            <label htmlFor="turma">Turma</label>
            <input 
                type="text" 
                name="turma"
                id="turma"
                value={turma}
                onChange={this.handleChange}
            />
            <input type="button" value="submit" onClick={this.submitForm}/>
        </form>
    )
}
  
export default Form;