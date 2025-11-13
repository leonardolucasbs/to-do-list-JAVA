import Login from './components/Login'
import './App.css'

function App() {

  return (
    <Box
          justifyContent="center"
          alignItems="center"
          display="flex"
          minHeight="80vh"
        >
          <Paper elevation={2} sx={{ p: 3, width: 320 }}>
            <Box textAlign="center" mb={2}>
              <Typography variant="h6" component="h2" fontWeight={600} mb={1}>
                Bem-vindo
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Faça login para acessar o sistema
              </Typography>
            </Box>
          </Paper>
        </Box>
  )
}

export default App
