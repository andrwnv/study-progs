import './App.css';
import { useState } from 'react';

function Box({id, handler, jsxObject}) {
    return (
        <div onClick={() => handler(id)}>
            {jsxObject}
        </div>
    )
}

const possibleMoves = {
    1: [2, 4],
    2: [3, 5],
    3: [7],
    4: [1, 11],
    5: [2, 6, 8],
    6: [10],
    7: [6],
    8: [12],
    9: [5, 13],
    10: [6, 13],
    11: [4],
    12: [11],
    13: [9]
}

const pressedColor = '#acd6ff';

function App() {
    const [sequence, updateSequence]  = useState([]);

    const handlePress = (id) => {
        if (sequence.length === 0)
            updateSequence([...sequence, id]);

        if (sequence.length >= 1) {
            if (possibleMoves[sequence[sequence.length - 1]].indexOf(id) !== -1)
                updateSequence([...sequence, id]);
            else
                updateSequence([]);
        }
    }

    const isCanColorize = (id) => {
        return sequence.indexOf(id) !== -1;
    }

    return (
        <div className = 'App'>
            {/* Col 1 */}
            <div style={{width: '400px', display: 'flex', justifyContent: 'space-between'}}>
                <Box
                    jsxObject={<div style={isCanColorize(1) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                    handler={handlePress}
                    id={1}
                />
                <div style={{display: 'flex'}}>
                    <Box
                        jsxObject={<div style={isCanColorize(2) ? {backgroundColor: pressedColor, width: '338px'} : {width: '338px'}} className='long-horizontal-box'/>}
                        handler={handlePress}
                        id={2}
                    />
                    <Box
                        jsxObject={<div style={isCanColorize(3) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                        handler={handlePress}
                        id={3}
                    />
                </div>
            </div>

            {/* Col 2 */}
            <div style={{width: '300px', display: 'flex', justifyContent: 'space-between'}}>
                <div style={{display: 'table'}}>
                    <Box jsxObject={<div style={isCanColorize(4) ? {backgroundColor: pressedColor} : {}} className= {'long-vertical-box'}/>}  handler={handlePress} id={4}/>
                </div>

                <div style={{display: 'flex', height: '170px', flexDirection: 'column'}}>
                    <div style={{width: '435px', display: 'flex', justifyContent: 'space-between'}}>
                        <Box
                            jsxObject={<div style={isCanColorize(5) ? {backgroundColor: pressedColor} : { }} className='box'/>}
                            handler={handlePress}
                            id={5}
                        />
                        <Box
                            jsxObject={<div style={isCanColorize(6) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                            handler={handlePress}
                            id={6}
                        />
                        <Box
                            jsxObject={<div style={isCanColorize(7) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                            handler={handlePress}
                            id={7}
                        />
                    </div>
                    <div style={{width: '435px', display: 'flex', justifyContent: 'space-between', marginTop: 'auto'}}>
                        <Box
                            jsxObject={<div style={isCanColorize(8) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                            handler={handlePress}
                            id={8}
                        />
                        <Box
                            jsxObject={<div style={isCanColorize(9) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                            handler={handlePress}
                            id={9}
                        />
                        <Box
                            jsxObject={<div style={isCanColorize(10) ? {backgroundColor: pressedColor} : {}} className='long-horizontal-box'/>}
                            handler={handlePress}
                            id={10}
                        />
                    </div>
                </div>
            </div>

            {/* Col 3 */}
            <div style={{width: '600px', display: 'flex'}}>
                <Box
                    jsxObject={<div style={isCanColorize(11) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                    handler={handlePress}
                    id={11}
                />

                <div style={{display: 'flex', justifyContent: 'space-between', width: '435px'}}>
                    <Box
                        jsxObject={<div style={isCanColorize(12) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                        handler={handlePress}
                        id={12}
                    />

                    <Box
                        jsxObject={<div style={isCanColorize(13) ? {backgroundColor: pressedColor} : {}} className='box'/>}
                        handler={handlePress}
                        id={13}
                    />
                </div>
            </div>
        </div>
    );
}

export default App;
