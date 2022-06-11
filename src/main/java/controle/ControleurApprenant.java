package controle;

import dao.ServiceApprenant;
import meserreurs.MonException;
import domain.Apprenant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Controleur
 */
@WebServlet(name = "/ServletControleurApprenant" ,urlPatterns={"/ServletControleurApprenant"})
public class ControleurApprenant extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ACTION_TYPE = "action";
    private static final String LISTER_Apprenant = "listerApprenant";
    private static final String AJOUTER_Apprenant = "ajouterApprenant";
    private static final String INSERER_Apprenant = "insererApprenant";
    private static final String MODIFIER_Apprenant = "modifierApprenant";
    private static final String MODIFIER_Apprenant_ACTION = "modifierApprenantAction";
    private static final String SUPPRIMER_Apprenant = "supprimerApprenant";
    private static final String ERROR_KEY = "mesErreurs";
    private static final String ERROR_PAGE = "/erreur.jsp";

    private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurApprenant() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        processusTraiteRequete(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        processusTraiteRequete(request, response);
    }

    protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;

        // execute l'action
        if (LISTER_Apprenant.equals(actionName)) {
            try {
                session = request.getSession();
                if (session.getAttribute("id").equals(null)) {

                    String message = "Vous n'êtes pas connecté !!";
                    request.setAttribute("message", message);
                    destinationPage = "/vues/formLogin.jsp";
                }
                else
                {
                    ServiceApprenant unServiceApprenant = new ServiceApprenant();
                    request.setAttribute("mesApprenants", unServiceApprenant.consulterListeApprenants());
                    destinationPage = "/vues/listerApprenant.jsp";
                }
            } catch (MonException e) {
                // TODO Auto-generated catch block
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        }
        else
        if (AJOUTER_Apprenant.equals(actionName)) {
            if (session.getAttribute("id").equals(null)) {

                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "/vues/formLogin.jsp";
            }
            else
            destinationPage = "/vues/ajouterApprenant.jsp";
        } else if (INSERER_Apprenant.equals(actionName)) {
            try {
                Apprenant unApprenant = new Apprenant();
                unApprenant.setNomApprenant(request.getParameter("txtnom"));
                unApprenant.setPrenomApprenant(request.getParameter("txtprenom"));
                ServiceApprenant unServiceApprenant = new ServiceApprenant();
                unServiceApprenant.insererApprenant(unApprenant);
                destinationPage = "/index.jsp";
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }

        } else if (MODIFIER_Apprenant.equals(actionName)) {
            try{
                ServiceApprenant unServiceApprenant = new ServiceApprenant();
                int numeroApprenant = Integer.valueOf(request.getParameter("id"));
                request.setAttribute("Apprenant", unServiceApprenant.consulterApprenant(numeroApprenant));
                destinationPage = "/vues/modifierApprenant.jsp";
            } catch(MonException e){
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        } else if (MODIFIER_Apprenant_ACTION.equals(actionName)) {
            try {
                Apprenant unApprenant = new Apprenant();

                unApprenant.setNumApprenant(Integer.parseInt(request.getParameter("idApprenant")));
                unApprenant.setNomApprenant(request.getParameter("txtnom"));
                unApprenant.setPrenomApprenant(request.getParameter("txtprenom"));

                ServiceApprenant unServiceApprenant = new ServiceApprenant();
                unServiceApprenant.modifierApprenant(unApprenant);

                //On récupère les adhérents pour rediriger vers la même page
                request.setAttribute("mesApprenants", unServiceApprenant.consulterListeApprenants());
                destinationPage = "/vues/listerApprenant.jsp";

            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        } else if (SUPPRIMER_Apprenant.equals(actionName)) {
            try {
                int idApprenant = Integer.parseInt(request.getParameter("id"));

                ServiceApprenant unServiceApprenant = new ServiceApprenant();

                //On supprime l'adhérent par son id
                unServiceApprenant.supprimerApprenant(idApprenant);

                //On récupère les adhérents pour rediriger vers la même page
                request.setAttribute("mesApprenants", unServiceApprenant.consulterListeApprenants());
                destinationPage = "/vues/listerApprenant.jsp";

            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        }

        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
            destinationPage = "/vues/Erreur.jsp";
        }
        // Redirection vers la page jsp appropriee
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);

    }



}
