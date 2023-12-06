import React, { useState } from 'react';

const ScheduleForm = ({ onChange, disabled }) => {
  const [scheduleData, setScheduleData] = useState({
    day: '',
    month: '',
    year: '',
    hour: '',
    minute: '',
  });

  const getFormattedDate = () => {
    const { day, month, year, hour, minute } = scheduleData;
    return `${month} ${day} ${year} ${hour}:${minute}`;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (!disabled) {
      setScheduleData((prevSchedule) => ({
        ...prevSchedule,
        [name]: value,
      }));
    }
    onChange(getFormattedDate());
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Adicione lógica de submissão se necessário
  };

  return (
    <form onSubmit={handleSubmit} className="schedule-form-container">
      <div className="schedule-form-section">
        <label>
          Dia:
          <input
            type="text"
            name="day"
            onChange={handleChange}
            value={scheduleData.day}
            required
          />
        </label>
        <label>
          Mês:
          <input
            type="text"
            name="month"
            onChange={handleChange}
            value={scheduleData.month}
            required
          />
        </label>
        <label>
          Ano:
          <input
            type="text"
            name="year"
            onChange={handleChange}
            value={scheduleData.year}
            required
          />
        </label>
        <label>
          Hora:
          <input
            type="text"
            name="hour"
            onChange={handleChange}
            value={scheduleData.hour}
            required
          />
        </label>
        <label>
          Minuto:
          <input
            type="text"
            name="minute"
            onChange={handleChange}
            value={scheduleData.minute}
            required
          />
        </label>
      </div>
    </form>
  );
};

export default ScheduleForm;
