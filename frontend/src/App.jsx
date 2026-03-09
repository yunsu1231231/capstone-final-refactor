import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import MemberFlow from './pages/MemberFlow';
import CreateRequest from './pages/CreateRequest';
import MemberRequestList from './pages/MemberRequestList';
import TrainerFlow from './pages/TrainerFlow';
import ChatRoom from './pages/ChatRoom';
import './App.css';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="member/:memberId" element={<MemberFlow />}>
          <Route index element={<CreateRequest />} />
          <Route path="requests" element={<MemberRequestList />} />
        </Route>
        <Route path="trainer/:trainerId" element={<TrainerFlow />} />
        <Route path="chat/:chatRoomId" element={<ChatRoom />} />
      </Routes>
    </BrowserRouter>
  );
}
