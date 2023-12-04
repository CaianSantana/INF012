import { useEffect, useState } from "react";

const TableHeader = ({ columns }) => {
    return (
      <thead>
        <tr>
          {columns.map((column, index) => (
            <th key={index}>{column}</th>
          ))}
          <th>Ações</th>
        </tr>
      </thead>
    );
  };
  
  const TableBody = ({ data, columns, onDelete }) => {
    console.log('data:', data);
    console.log('columns:', columns);
    return (
      <tbody>
      {data.map((row) => (
        <tr key={row.name} >
         
          {columns.map((column, colIndex) => (
            <td key={colIndex}>{row[column]}</td>
          ))}
          
          <td>
            <button onClick={() => onDelete(row.name)}>
              Apagar
            </button>
          </td>
        </tr>
      ))}
    </tbody>
    );
  };
  
  const EntityTable = ({ fetchData, onDelete, columns }) => {
    const [data, setData] = useState([]);
  
    useEffect(() => {
      const fetchDataAndSetData = async () => {
        try {
          const fetchedData = await fetchData();
          console.log('Dados recebidos:', fetchedData);
          setData(fetchedData);
        } catch (error) {
          console.error('Erro ao obter dados', error);
        }
      };
  
      fetchDataAndSetData();
    }, [fetchData]);
  
    return (
      <table>
        <TableHeader columns={columns} />
        <TableBody data={data} columns={columns} onDelete={onDelete} />
      </table>
    );
  };
  
  export { EntityTable };