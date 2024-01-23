import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            light: '#e8f5e9',
            main: '#2e7d32',
            dark: '#051a09',
            contrastText: '#000000',
            textColor: '#FFFFFF',
        },
        secondary: {
            // Satunnaisia väriarvoja, voi kattoa tarkemmat värit myöhemmin
            light: '#ff7961',
            main: '#f44336',
            dark: '#ba000d',
            contrastText: '#000',
        },
        background: {
            default: '#e8f5e9',
        },
    },
});

export default theme;
