import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "../pages/Home";
import { Result } from "../pages/Result";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="result/:cityId" element={<Result />} />
        <Route path="/" element={<Home />}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
