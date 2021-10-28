import { useState } from 'react';

import './App.css';

const replaceAt = (str, index, value) => {
    return <>
        {str.substr(0, index)}
        {value}
        {str.substr(index + 1, str.length - 1)}
    </>;
}

function App() {
    const [selectedFileName, setFileName] = useState('new_file.txt');
    const [userData, updateUserData] = useState('');
    const [currentIndex, updateIndex] = useState(-1);

    let fileReader = null;

    const handleFileRead = (_) => {
        console.log('test')
        updateUserData(fileReader.result);
        updateIndex(0);

        fileReader = null;
    }

    const handleFileError = () => {
        alert('Cant open selected file!');
    }

    const handleFileChoose = (file) => {
        fileReader = new FileReader();
        fileReader.onloadend = handleFileRead;
        fileReader.onerror = handleFileError;
        fileReader.filename = file;
        fileReader.readAsText(file);

        setFileName(file.name.replace(/\.[^/.]+$/, ''));
    }

    const handleSaveFile = () => {
        const blob = new Blob([userData], {type: 'text/plain'});
        const fileName = selectedFileName;

        let link = document.createElement('a');
        link.download = fileName;

        link.href = window.URL.createObjectURL(blob);
        link.style.display = 'none';
        document.body.appendChild(link);

        link.click();
    }

    const isCharKeycode = (keycode) => {
        return (keycode >= 48 && keycode <= 57) ||
            (keycode >= 65 && keycode <= 90) ||
            (keycode >= 96 && keycode <= 105) || (keycode >= 186 && keycode <= 222) ||
            keycode === 106 || keycode === 111 || keycode === 109 || keycode === 110 || keycode === 107;
    }

    const handleKeyPress = (event) => {
        console.log(event.keyCode)
        switch (event.keyCode) {
            case 35: { // END
                const spaceIndex = userData.indexOf(' ', currentIndex);
                if (spaceIndex > 0)
                    updateIndex(spaceIndex - 1);
                break;
            }
            case 36: { // HOME
                const lastSpaceIndex = userData.lastIndexOf(' ', currentIndex);

                if (lastSpaceIndex > 0)
                    updateIndex(lastSpaceIndex + 1);

                break;
            }

            case 32: { // SPACE
                updateUserData(`${userData.slice(0, currentIndex)} ${userData.slice(currentIndex)}`)
                updateIndex(currentIndex + 1)

                break;
            }
            case 13: { // ENTER
                updateUserData(`${userData.slice(0, currentIndex)}\n${userData.slice(currentIndex)}`)
                updateIndex(currentIndex + 1);

                break;
            }

            case 37: { // ARR_LEFT
                if (currentIndex > 0)
                    updateIndex(currentIndex - 1);

                break;
            }
            case 38: { // ARR_TOP
                const prevNewline = userData.lastIndexOf('\n', currentIndex);
                const newIndexPosition = userData.lastIndexOf('\n', prevNewline - 1);

                updateIndex(newIndexPosition + (currentIndex - prevNewline));

                break;
            }
            case 39: { // ARR_RIGHT
                if (currentIndex < userData.length)
                    updateIndex(currentIndex + 1);
                break;
            }
            case 40: { // ARR_BOTTOM
                const prevNewline = userData.lastIndexOf('\n', currentIndex);
                const nestNewline = userData.indexOf('\n', currentIndex);
                const newIndexPosition = nestNewline + (currentIndex - prevNewline);

                if (newIndexPosition < userData.length)
                    updateIndex(newIndexPosition);
                else
                    updateIndex(userData.length - 2);

                break;
            }

            case 8: { // BACKSPACE
                updateUserData(userData.slice(0, currentIndex - 1) + userData.slice(currentIndex));
                updateIndex(currentIndex - 1);
                break;
            }
            case 46: { // DEL
                updateUserData(userData.slice(0, currentIndex + 1) + userData.slice(currentIndex + 2, userData.length));
                break;
            }
            default:
                break;
        }

        if (isCharKeycode(event.keyCode)) {
            updateUserData(userData + event.key)
            updateIndex(currentIndex + 1)
        } else if (isCharKeycode(event.keyCode) && event.shiftKey) {
            updateUserData(userData + String.fromCharCode(event.keyCode + 32))
            updateIndex(currentIndex + 1)
        }
    }

    const underlineCurrentPosition = () => {
        return <>
            {
                replaceAt(userData, currentIndex,
                    <span
                        style = {{
                            textDecoration: 'underline'
                        }}
                    >
                        {userData.charAt(currentIndex)}
                    </span>
                )
            }
        </>;
    }

    return (
        <div
            className = 'App'
            tabIndex = '0'
            onKeyDown = {e => handleKeyPress(e)}
        >
            <label className = 'file-upload'>
                <input
                    type = 'file'
                    id = 'file'
                    className = 'input-file'
                    accept = '.txt'
                    onChange = {e => {
                        handleFileChoose(e.target.files[0]);
                        e.target.value = null;
                    }}
                />
                Upload File
            </label>

            <div
                style = {{
                    whiteSpace: 'pre-wrap',
                    textAlign: 'left',
                    width: '50%',
                    color: '#d7eaff',
                    backgroundColor: '#6a93b2',
                    paddingTop: '5px',
                    paddingLeft: '10px',
                    paddingRight: '10px',
                    paddingBottom: '10px',
                    borderRadius: '5px',
                    marginBottom: '5vh'
                }}
            >
                {
                    underlineCurrentPosition()
                }
            </div>

            <button
                className = 'save_button'
                onClick = {handleSaveFile}
            >
                Download edited file
            </button>
        </div>
    );
}

export default App;
