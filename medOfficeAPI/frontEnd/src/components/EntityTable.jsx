import { useEffect, useState } from "react";
import EventBus from '../eventBus';

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

const TableBody = ({ data, columns, onDelete, onEdit }) => {
  return (
    <tbody>
      {data.map((row) => (
        <tr key={row.id}>
          {columns.map((column, colIndex) => (
            <td key={colIndex}>{row[column]}</td>
          ))}
          <td>
            <button onClick={() => onDelete(row.id)}>
              Apagar
            </button>
            <button onClick={() => onEdit(row)}>
              Editar
            </button>
          </td>
        </tr>
      ))}
    </tbody>
  );
};


const EntityTable = ({ fetchData, onDelete, onEdit, columns }) => {
  const [data, setData] = useState([]);

  const handleDelete = async (id) => {
    try {
      await onDelete(id);
      setData((prevData) => prevData.filter((item) => item.id !== id));
    } catch (error) {
      console.error('Erro ao remover item', error);
    }
  };

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

    // Subscreva ao evento 'patientAdded' para atualizar os dados quando um novo paciente for adicionado
    EventBus.subscribe('patientAdded', fetchDataAndSetData);

    // Remova a assinatura do evento ao desmontar o componente
    return () => {
      EventBus.unsubscribe('patientAdded', fetchDataAndSetData);
    };
  }, [fetchData]);

  return (
    <table>
      <TableHeader columns={columns} />
      <TableBody
        data={data}
        columns={columns}
        onDelete={handleDelete}
        onEdit={onEdit} // Passando a função onEdit para o TableBody
      />
    </table>
  );
};

export { EntityTable };
