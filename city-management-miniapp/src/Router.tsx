import { MemoryRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/Home";

type RouterProps = {
  currentPath: string;
};

const Router = ({ currentPath }: RouterProps) => {
  return (
    <MemoryRouter initialEntries={[currentPath]}>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </MemoryRouter>
  );
};

export default Router;
