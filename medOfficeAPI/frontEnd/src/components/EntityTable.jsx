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
    return (
      <tbody>
        {data.map((row, rowIndex) => (
          <tr key={rowIndex}>
            {columns.map((column, colIndex) => (
              <td key={colIndex}>{row[column]}</td>
            ))}
            <td>
              <button onClick={() => onDelete(row.id)}>
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