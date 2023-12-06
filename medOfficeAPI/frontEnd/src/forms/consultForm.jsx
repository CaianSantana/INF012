import { useEffect, useState } from "react";
import ScheduleForm from "../components/SchedulingForm";
import { addConsult, deleteConsult } from "../services/consultApi";
import EventBus from "../eventBus";

const ConsultForm = ({ selectedConsult }) => {
  const [editing, setEditing] = useState(false);
  const [consultData, setConsultData] = useState({
    cpf: '',
    crm: '',
    scheduling: {
      schedule: '',
    }
  });
  const [cancelData, setCancelData] = useState({
    cancelReason: ""
  })

  const handleScheduleChange = (updatedSchedule) => {
    setConsultData((prevData) => ({
      ...prevData,
      scheduling: { schedule: updatedSchedule },
    }));
  };

  const resetData = () =>{
    setConsultData({cpf: '',
    crm: '',
    scheduling: {
      schedule: '',
    }
  });
    setCancelData({
      cancelReason: ""
    });
  }
  const handleCancelEdit = () => {
    setEditing(false);
    resetData()
  };

  useEffect(() => {
    if (selectedConsult) {
      setEditing(true)
      setConsultData({
        cpf: selectedConsult.cpf || '',
        crm: selectedConsult.crm || '',
        scheduling: {
          schedule: selectedConsult.scheduling?.schedule || '',
        },
      });
      setCancelData({
        cancelReason: ""
      })
    } else {
      setEditing(false);
      resetData()
    }
  }, [selectedConsult]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updatedData = {
        cpf: consultData.cpf,
        crm: consultData.crm || '',
        scheduling: consultData.scheduling || null,
      };
      const updatedCancelData={
        cancelReason: cancelData.cancelReason || ""
      }
      console.log(updatedData)
      if(selectedConsult){
        console.log(updatedCancelData.cancelReason)
        await deleteConsult(selectedConsult.id, updatedCancelData.cancelReason);
        EventBus.publish("itemAdded")
      }else{
        const response = await addConsult(updatedData);
        console.log('Consulta adicionada com sucesso');
        console.log('Dados da consulta adicionados com sucesso:', response);
        EventBus.publish("itemAdded")
      }
      resetData()
    } catch (error) {
      console.error('Erro durante a solicitação:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-section">
        <label htmlFor="cpf">CPF:</label>
        <input
          type="text"
          id="cpf"
          name="cpf"
          value={consultData.cpf}
          onChange={(e) => setConsultData({ ...consultData, cpf: e.target.value })}
          required
          disabled={selectedConsult !== null} // Desabilita se uma consulta estiver selecionada
        />
      </div>

      <div className="form-section">
        <label htmlFor="crm">CRM:</label>
        <input
          type="text"
          id="crm"
          name="crm"
          value={consultData.crm}
          onChange={(e) => setConsultData({ ...consultData, crm: e.target.value })}
          required
          disabled={selectedConsult !== null} // Desabilita se uma consulta estiver selecionada
        />
      </div>

      <div className="form-section">
        <label htmlFor="cancelReason">Motivo do Cancelamento:</label>
        <input
          type="text"
          id="cancelReason"
          name="cancelReason"
          value={consultData.cancelReason}
          onChange={(e) => setCancelData({...cancelData, cancelReason: e.target.value })}
          required
          disabled={selectedConsult === null} // Desabilita se uma consulta estiver selecionada
        />
      </div>

      <div className="form-section schedule-form">
        <label>Agendamento:</label>
        <ScheduleForm onChange={handleScheduleChange} disabled={editing} />
      </div>

      {/* Exiba a string formatada */}
      <div className="form-section">
        <p>Data formatada: {consultData.scheduling.schedule}</p>
      </div>

      <div className="button-container">
        <button type="submit">
          {editing ? 'Cancelar Consulta' : 'Marcar Consulta'}
        </button>
        {editing && (
          <button type="button" onClick={handleCancelEdit}>
            Voltar
          </button>
        )}
      </div>
    </form>
  );
};

export default ConsultForm;


