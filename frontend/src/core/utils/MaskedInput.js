import InputMask from 'react-input-mask';

//const onlyNumbers = (str) => str.replace(/[^0-9]/g, ''); // Para retirar os números

const MaskedInput = ({ value, onChange, name, mask, disabled, className, placeholder }) => {

    function handleChange(event) {
        onChange({
            ...event,
            target: {
                ...event.target,
                name,
                //value: onlyNumbers(event.target.value)
                value: event.target.value
            }
        });
    }

    return (
        <InputMask
            name={name}
            mask={mask}
            value={value}
            onChange={handleChange}
            maskChar={null}
            disabled={disabled}
            className={className}
            placeholder={placeholder}
        />
    );
};

export default MaskedInput;