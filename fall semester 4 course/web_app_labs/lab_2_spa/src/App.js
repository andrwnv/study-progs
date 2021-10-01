import RecordsList from './components/RecordsList';
import { Typography } from '@mui/material';

export default function App() {
    return (
        <div
            style = {{
                width: '100%',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                flexDirection: 'column'
            }}
        >
          <Typography sx={{ fontSize: 30, fontWeight: 700, mb: 1.5 }} style={{marginTop: '1.5%'}}>
              Records
          </Typography>
          <RecordsList />
      </div>
    );
}
