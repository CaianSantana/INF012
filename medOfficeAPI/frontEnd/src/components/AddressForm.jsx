import React, { useState } from 'react';

const AddressForm = ({ onChange, disabled  }) => {
    const [addressData, setAddressData] = useState({
      publicPlace: '',
      number: '',
      complement: '',
      neighborhood: '',
      city: '',
      state: '',
      zipCode: '',
    });
  
    const handleChange = (e) => {
      const { name, value } = e.target;
  
      if (!disabled) {
        setAddressData((prevAddress) => ({
          ...prevAddress,
          [name]: value,
        }));
      }
  
      const { address, ...restPatientData } = addressData;
      onChange({
        ...restPatientData,
        address: {
          ...address,
          [name]: value,
        },
      });
    };

    return (
        <div className="address-form-container">
          <div className="address-form-section">
            <label>
              Rua:
              <input
                type="text"
                name="publicPlace"
                onChange={handleChange}
                required
                value={addressData.publicPlace}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              Número:
              <input
                type="text"
                name="number"
                onChange={handleChange}
                value={addressData.number}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              Complemento:
              <input
                type="text"
                name="complement"
                onChange={handleChange}
                value={addressData.complement}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              Bairro:
              <input
                type="text"
                name="neighborhood"
                onChange={handleChange}
                required
                value={addressData.neighborhood}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              Cidade:
              <input
                type="text"
                name="city"
                onChange={handleChange}
                required
                value={addressData.city}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              Estado:
              <input
                type="text"
                name="state"
                onChange={handleChange}
                required
                value={addressData.state}
                disabled={disabled}
              />
            </label>
          </div>
    
          <div className="address-form-section">
            <label>
              CEP:
              <input
                type="text"
                name="zipCode"
                onChange={handleChange}
                required
                value={addressData.zipCode}
                disabled={disabled}
              />
            </label>
          </div>
        </div>
  );
};

export default AddressForm;