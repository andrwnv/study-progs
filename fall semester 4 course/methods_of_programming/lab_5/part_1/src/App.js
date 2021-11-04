import './App.css';

function Box({jsxObject}) {
    return (
        jsxObject
    )
}

function App() {
    return (
        <div className = 'App'>
            {/* Col 1 */}
            <div style={{width: '510px', display: 'flex', justifyContent: 'space-between'}}>
                <Box jsxObject={<div className='box' onClick={() => {console.log(1)}}/>}/>
                <Box jsxObject={<div className='long-horizontal-box' onClick={() => {console.log(2)}} style={{width: '338px'}}/>}/>
                <Box jsxObject={<div className='box' onClick={() => {console.log(3)}}/>}/>
            </div>

            {/* Col 2 */}
            <div style={{width: '300px', display: 'flex', justifyContent: 'space-between'}}>
                <div style={{display: 'table'}}>
                    <Box jsxObject={<div className= {'long-vertical-box'} onClick={() => {console.log(4)}}/>}/>
                </div>

                <div style={{display: 'flex', height: '170px', flexDirection: 'column'}}>
                    <div style={{width: '435px', display: 'flex', justifyContent: 'space-between'}}>
                        <Box jsxObject={<div className='box' onClick={() => {console.log(5)}}/>}/>
                        <Box jsxObject={<div className='box' onClick={() => {console.log(6)}}/>}/>
                        <Box jsxObject={<div className='box' onClick={() => {console.log(7)}}/>}/>
                    </div>
                    <div style={{width: '435px', display: 'flex', justifyContent: 'space-between', marginTop: 'auto'}}>
                        <Box jsxObject={<div className='box' onClick={() => {console.log(8)}}/>}/>
                        <Box jsxObject={<div className='box' onClick={() => {console.log(9)}}/> }/>
                        <Box jsxObject={<div className='long-horizontal-box' onClick={() => {console.log(10)}}/>}/>
                    </div>
                </div>
            </div>

            {/* Col 3 */}
            <div style={{width: '600px', display: 'flex'}}>
                <Box jsxObject={<div className='box' onClick={() => {console.log(11)}}/>}/>

                <div style={{display: 'flex', justifyContent: 'space-between', width: '435px'}}>
                    <Box jsxObject={<div className='box' onClick={() => {console.log(12)}}/>}/>
                    <Box jsxObject={<div className='box' onClick={() => {console.log(13)}}/>}/>
                </div>
            </div>
        </div>
    );
}

export default App;
